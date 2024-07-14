package me.sepehrasadiyan.wallet_v2.controller.query;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.response.DepositResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/query")
@RequiredArgsConstructor
@Validated
public class QueryController {

    @GetMapping("/{userId}")
    public ResponseEntity<DepositResponseDto> receiveGeographicalData(@PathVariable(name = "userId") Long userId) {

        return null;
    }

}