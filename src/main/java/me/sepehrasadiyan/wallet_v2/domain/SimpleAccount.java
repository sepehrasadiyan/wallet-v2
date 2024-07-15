package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.AccountTypeEnum;
import me.sepehrasadiyan.wallet_v2.common.internal.JournalOperationEnum;
import me.sepehrasadiyan.wallet_v2.exception.AccountBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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


    public BigDecimal withdraw(BigDecimal withdraw) {
        return null;
    }

    public boolean validateCurrentBalance() {
        BigDecimal counter = BigDecimal.ZERO;
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

}
