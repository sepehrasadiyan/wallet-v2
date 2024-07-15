package me.sepehrasadiyan.wallet_v2.services.query;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleAccountService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueryServiceImpl implements QueryHandler{


    private final SimpleAccountService simpleAccountService;

    public QueryServiceImpl(SimpleAccountService simpleAccountService) {
        this.simpleAccountService = simpleAccountService;
    }

    @Override
    public BalanceResponseDto getUserBalance(Long userId) {

        return null;
    }
}
