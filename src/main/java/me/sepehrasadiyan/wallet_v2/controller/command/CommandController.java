package me.sepehrasadiyan.wallet_v2.controller.command;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.request.DepositRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.DepositResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/command")
@RequiredArgsConstructor
@Validated
public class CommandController {

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDto> receiveGeographicalData(@RequestBody @Valid DepositRequestDto request) {

        return null;
    }

}