package me.sepehrasadiyan.wallet_v2.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountRequestDto {

    private String name;
    private String lastname;
    private BigDecimal initBalance;
}
