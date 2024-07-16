package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.AccountTypeEnum;
import me.sepehrasadiyan.wallet_v2.common.internal.JournalOperationEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simple_journal")
@NoArgsConstructor
public class SimpleJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount_change", nullable = false)
    private BigDecimal amount_change;


    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AccountTypeEnum accountTypeEnum;

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private JournalOperationEnum journalOperationEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    private SimpleAccount account;

    @Column(name = "reference_id", nullable = false, updatable = false)
    private Long referenceId;

    @Column(name = "creating_time", nullable = false, updatable = false)
    private LocalDateTime creatingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount_change() {
        return amount_change;
    }

    public void setAmount_change(BigDecimal amount_change) {
        this.amount_change = amount_change;
    }

    public AccountTypeEnum getAccountTypeEnum() {
        return accountTypeEnum;
    }

    public void setAccountTypeEnum(AccountTypeEnum accountTypeEnum) {
        this.accountTypeEnum = accountTypeEnum;
    }

    public JournalOperationEnum getJournalOperationEnum() {
        return journalOperationEnum;
    }

    public void setJournalOperationEnum(JournalOperationEnum journalOperationEnum) {
        this.journalOperationEnum = journalOperationEnum;
    }

    public SimpleAccount getAccount() {
        return account;
    }

    public void setAccount(SimpleAccount account) {
        this.account = account;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    @PrePersist
    protected void onCreate() {
        creatingTime = LocalDateTime.now();
    }
}
