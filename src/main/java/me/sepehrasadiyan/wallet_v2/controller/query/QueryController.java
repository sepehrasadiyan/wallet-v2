package me.sepehrasadiyan.wallet_v2.controller.query;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;
import me.sepehrasadiyan.wallet_v2.services.query.QueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/query")
@RequiredArgsConstructor
@Validated
public class QueryController {

    private final QueryHandler queryHandler;

    @GetMapping("/{userId}")
    public ResponseEntity<BalanceResponseDto> receiveBalanceData(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(queryHandler.getUserBalance(userId));
    }

}