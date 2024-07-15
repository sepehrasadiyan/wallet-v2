package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.AccountTypeEnum;
import me.sepehrasadiyan.wallet_v2.common.internal.JournalOperationEnum;
import me.sepehrasadiyan.wallet_v2.exception.AccountBalanceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Table(name = "simple_account")
@AllArgsConstructor
@NoArgsConstructor
@Data
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SimpleUser user;

    @PersistenceContext
    @Transient
    private EntityManager entityManager;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SimpleJournal> journals = new ArrayList<>();
    public SimpleJournal depositAmount(BigDecimal deposit) {
        if (!validateCurrentBalance())
            throw new AccountBalanceException("balance is wrong");
        
        balance.add(deposit);
        SimpleJournal journal = new SimpleJournal();
        journal.setAmount_change(deposit);
        journal.setAccountTypeEnum(this.accountTypeEnum);
        journal.setAccount(this);
        journal.setReferenceId(getNextReferenceNumber());
        this.journals.add(journal);
        return journal;
    }


    public BigDecimal withdraw(BigDecimal withdraw) {
        return null;
    }

    public boolean validateCurrentBalance() {
        BigDecimal counter = BigDecimal.ZERO;
        for (SimpleJournal simpleJournal : this.getJournals()) {
            if (simpleJournal.getJournalOperationEnum().equals(JournalOperationEnum.DEPOSIT)) {
                counter.add(simpleJournal.getAmount_change());
            }
            else if (simpleJournal.getJournalOperationEnum().equals(JournalOperationEnum.WITHDRAW)) {
                counter.subtract(simpleJournal.getAmount_change());
            }
        }
        return counter.equals(this.balance);
    }

    private Long getNextReferenceNumber() {
        return ((Number) entityManager.createNativeQuery("SELECT nextval('reference_number_seq')").getSingleResult()).longValue();
    }
}
