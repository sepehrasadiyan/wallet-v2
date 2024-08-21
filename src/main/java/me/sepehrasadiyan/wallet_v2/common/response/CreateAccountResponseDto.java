package me.sepehrasadiyan.wallet_v2.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountResponseDto {

    private String accountNumber;
}
