package me.sepehrasadiyan.wallet_v2.common.response;


import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ToString
@NoArgsConstructor
public class BalanceResponseDto {
    private BigDecimal balance;

    public BalanceResponseDto(BigDecimal balance) {
        this.balance = balance.setScale(0, RoundingMode.DOWN);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(0, RoundingMode.DOWN);
    }
}
