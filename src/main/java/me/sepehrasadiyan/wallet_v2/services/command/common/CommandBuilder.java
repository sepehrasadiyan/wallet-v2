package me.sepehrasadiyan.wallet_v2.services.command.common;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CommandBuilder<T>  {

    private T requestBody;

    private String actionName = null;

    private String accountNumber = null;

    private BigDecimal amount;

    public CommandResource<T> build() {
        return new CommandResource<>(requestBody, actionName, accountNumber, amount);
    }

    public CommandBuilder<T> requestDto(final T requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CommandBuilder<T> createDepositCommand(String accountNumber, BigDecimal amount) {
        this.actionName = "deposit";
        this.accountNumber = accountNumber;
        this.amount = amount;
        return this;
    }

    public CommandBuilder<T> createWithdrawalCommand(String accountNumber, BigDecimal amount) {
        this.actionName = "withdrawal";
        this.accountNumber = accountNumber;
        this.amount = amount;
        return this;
    }

    public CommandBuilder<T> createAccount() {
        this.actionName = "createAccount";
        return this;
    }

}
