package me.sepehrasadiyan.wallet_v2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sepehrasadiyan.wallet_v2.common.request.DepositRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;
import me.sepehrasadiyan.wallet_v2.common.response.DepositResponseDto;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    @Order(1)
    void inquiry_with_exist_cif() throws Exception {


        MvcResult mvcResult = mvc.perform(get("/api/v1/query/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BalanceResponseDto balanceResponseDto = jsonToBalanceResponseDto(mvcResult, objectMapper);
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