package me.sepehrasadiyan.wallet_v2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sepehrasadiyan.wallet_v2.common.request.DepositRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;
import me.sepehrasadiyan.wallet_v2.common.response.DepositResponseDto;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.repository.SimpleUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WalletV2Application.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({ContainerConfiguration.class})
public class IntegrationTest {

    @Autowired
    MockMvc mvc;


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SimpleUserRepository simpleUserRepository;

    @Test
    @Order(1)
    void inquiry_user_balance_success() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/api/v1/query/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BalanceResponseDto balanceResponseDto = jsonToBalanceResponseDto(mvcResult, objectMapper);
    }

    @Test
    @Order(2)
    void make_deposit_success() throws Exception {

        MvcResult mvcResult = mvc.perform(post("/api/v1/command/deposit").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DepositRequestDto(BigDecimal.valueOf(123333),1L)))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BalanceResponseDto balanceResponseDto = jsonToBalanceResponseDto(mvcResult, objectMapper);
    }

    @Test
    @Order(3)
    void make_deposit_error() throws Exception {

        MvcResult mvcResult = mvc.perform(post("/api/v1/command/deposit").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DepositRequestDto(BigDecimal.valueOf(123333),12L)))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
    }

    @Test
    @Order(4)
    void inquiry_user_balance() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/api/v1/query/145").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(5)
    void inquiry_user_balanced() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/api/v1/query/12").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BalanceResponseDto balanceResponseDto = jsonToBalanceResponseDto(mvcResult, objectMapper);
        Assertions.assertEquals(BigDecimal.valueOf(200000), balanceResponseDto.getBalance());
    }

    @Test
    @Order(6)
    void inquiry_user_balance_first() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/api/v1/query/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BalanceResponseDto balanceResponseDto = jsonToBalanceResponseDto(mvcResult, objectMapper);
        Assertions.assertEquals(BigDecimal.valueOf(100000), balanceResponseDto.getBalance());
    }


    public static DepositResponseDto jsonToDepositResponseDto(MvcResult mvcResult, ObjectMapper objectMapper) throws Exception {
        String content = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(content, new TypeReference<DepositResponseDto>() {
        });
    }

    public static BalanceResponseDto jsonToBalanceResponseDto(MvcResult mvcResult, ObjectMapper objectMapper) throws Exception {
        String content = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(content, new TypeReference<BalanceResponseDto>() {
        });
    }

}