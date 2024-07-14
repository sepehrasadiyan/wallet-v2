package me.sepehrasadiyan.wallet_v2.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
@AllArgsConstructor
public class BalanceResponseDto {
    private BigDecimal balance;
}
