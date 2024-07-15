package me.sepehrasadiyan.wallet_v2.services.internal;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.domain.SimpleAccount;
import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.repository.SimpleAccountRepository;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class SimpleAccountService {

    private final SimpleAccountRepository simpleAccountRepository;

    public SimpleAccountService(SimpleAccountRepository simpleAccountRepository) {
        this.simpleAccountRepository = simpleAccountRepository;
    }

    @Transactional
    public CommandResult makeDeposit(SimpleUser user, BigDecimal amount) {
        List<SimpleAccount> accountList = simpleAccountRepository.findAllByUser(user);
        // The logic over here can change due to the business
        SimpleAccount simpleAccount = accountList.getFirst();
        SimpleJournal simpleJournal = simpleAccount.depositAmount(amount);
        simpleAccountRepository.save(simpleAccount);
        return new CommandResult(simpleJournal.getReferenceNumber());
    }
}
