package me.sepehrasadiyan.wallet_v2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserStatusException extends BusinessAbstractException {
    public UserStatusException(String message) {
        super("user.status : with message : " + message);
        log.error("user.status : with message : {}", message);
    }
}
