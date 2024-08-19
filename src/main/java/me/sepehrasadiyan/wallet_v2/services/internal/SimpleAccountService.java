package me.sepehrasadiyan.wallet_v2.services.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.domain.SimpleAccount;
import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.repository.SimpleAccountRepository;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class SimpleAccountService {

    private final SimpleAccountRepository simpleAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public SimpleAccountService(SimpleAccountRepository simpleAccountRepository) {
        this.simpleAccountRepository = simpleAccountRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public CommandResult makeDeposit(SimpleUser user, BigDecimal amount) {
        List<SimpleAccount> accountList = simpleAccountRepository.findAllByUser(user);
        // The logic over here can change due to the business
        SimpleAccount simpleAccount = accountList.getFirst();
        SimpleJournal simpleJournal = simpleAccount.depositAmount(amount);
        simpleJournal.setReferenceId(getNextReferenceNumber());
        simpleAccount.addJournal(simpleJournal);
        simpleAccountRepository.save(simpleAccount);
        return new CommandResult(simpleJournal.getReferenceId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public CommandResult makeWithdrawal(SimpleUser user, BigDecimal amount) {
        List<SimpleAccount> accountList = simpleAccountRepository.findAllByUser(user);
        // The logic over here can change due to the business
        SimpleAccount simpleAccount = accountList.getFirst();
        SimpleJournal simpleJournal = simpleAccount.withdraw(amount);
        simpleJournal.setReferenceId(getNextReferenceNumber());
        simpleAccount.addJournal(simpleJournal);
        simpleAccountRepository.save(simpleAccount);
        return new CommandResult(simpleJournal.getReferenceId());
    }


    public BigDecimal runningBalance(SimpleUser user) {
        List<SimpleAccount> accountList = user.getAccounts();
        return accountList.stream()
                .map(SimpleAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getNextReferenceNumber() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_next_reference_number");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.OUT);
        query.execute();
        return (Long) query.getOutputParameterValue(1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getNextAccountNumber() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_next_account_number");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.OUT);
        query.execute();
        return (Long) query.getOutputParameterValue(1);
    }
}
