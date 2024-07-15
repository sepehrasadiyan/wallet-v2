package me.sepehrasadiyan.wallet_v2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.sepehrasadiyan.wallet_v2.common.internal.AccountTypeEnum;
import me.sepehrasadiyan.wallet_v2.common.internal.JournalOperationEnum;

import java.math.BigDecimal;

@ToString
@Entity
@Table(name = "simple_journal")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @JoinColumn(name = "account_id", nullable = false)
    private SimpleAccount account;

    @Column(name = "reference_number", nullable = false, updatable = false)
    private Long referenceNumber;

}
