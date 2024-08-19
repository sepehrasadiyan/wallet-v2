package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.AccountTypeEnum;
import me.sepehrasadiyan.wallet_v2.common.internal.JournalOperationEnum;
import me.sepehrasadiyan.wallet_v2.exception.AccountBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.*;

@Entity
@Table(name = "simple_account")
public class SimpleAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;


    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AccountTypeEnum accountTypeEnum;


    @Version
    @Column(name = "version", nullable = false)
    private Long version;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private SimpleUser user;


    @Column(name = "account_number", nullable = false, length = 64)
    private String accountNumber;


    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private List<SimpleJournal> journals = new ArrayList<>();


    public SimpleJournal depositAmount(BigDecimal deposit) {
        if (!validateCurrentBalance())
            throw new AccountBalanceException("balance is wrong");
        
        balance.add(deposit);
        SimpleJournal journal = new SimpleJournal();
        journal.setAmount_change(deposit);
        journal.setAccountTypeEnum(this.accountTypeEnum);
        journal.setAccount(this);
        return journal;
    }


    public SimpleJournal withdraw(BigDecimal withdraw) {
        if (!validateCurrentBalance()) {
            throw new AccountBalanceException("balance is wrong");
        }

        balance.subtract(withdraw);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountBalanceException("balance is not enough to withdraw");
        }
        SimpleJournal journal = new SimpleJournal();
        journal.setAmount_change(withdraw);
        journal.setAccountTypeEnum(this.accountTypeEnum);
        journal.setAccount(this);
        return journal;
    }

    public boolean validateCurrentBalance() {
        BigDecimal counter = ZERO;
        for (SimpleJournal simpleJournal : this.getJournals()) {
            if (simpleJournal.getJournalOperationEnum().equals(JournalOperationEnum.DEPOSIT)) {
                counter = counter.add(simpleJournal.getAmount_change());            }
            else if (simpleJournal.getJournalOperationEnum().equals(JournalOperationEnum.WITHDRAW)) {
                counter = counter.subtract(simpleJournal.getAmount_change());            }
        }
        return counter.equals(this.balance);
    }

    public void addJournal(SimpleJournal journal) {
        if (journal != null) {
            journal.setAccount(this);
            journals.add(journal);
        } else {
            throw new IllegalArgumentException("Journal cannot be null");
        }
    }

    public List<SimpleJournal> getJournals() {
        if (journals == null) {
            journals = new ArrayList<>();
        }
        return journals;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountTypeEnum getAccountTypeEnum() {
        return accountTypeEnum;
    }

    public void setAccountTypeEnum(AccountTypeEnum accountTypeEnum) {
        this.accountTypeEnum = accountTypeEnum;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public void setJournals(List<SimpleJournal> journals) {
        this.journals = journals;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
