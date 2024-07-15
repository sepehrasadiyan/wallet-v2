package me.sepehrasadiyan.wallet_v2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountBalanceException extends BusinessAbstractException {
    public AccountBalanceException(String message) {
        super("account.balance : with message : " + message);
        log.error("account.balance : with message : {}", message);
    }
}