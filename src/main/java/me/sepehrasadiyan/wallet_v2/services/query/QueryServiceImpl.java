package me.sepehrasadiyan.wallet_v2.services.query;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.response.BalanceResponseDto;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleAccountService;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class QueryServiceImpl implements QueryHandler{


    private final SimpleAccountService simpleAccountService;

    private final SimpleUserService simpleUserService;

    public QueryServiceImpl(SimpleAccountService simpleAccountService, SimpleUserService simpleUserService) {
        this.simpleAccountService = simpleAccountService;
        this.simpleUserService = simpleUserService;
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceResponseDto getUserBalance(Long userId) {
        SimpleUser simpleUser = simpleUserService.getUserForProcessQuery(userId);
        return new BalanceResponseDto(simpleAccountService.runningBalance(simpleUser));
    }
}
