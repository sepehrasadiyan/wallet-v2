package me.sepehrasadiyan.wallet_v2.services.query;

import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;

public interface QueryHandler {
    BalanceResponseDto getUserBalance(Long userId);

}
