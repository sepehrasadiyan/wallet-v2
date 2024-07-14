package me.sepehrasadiyan.wallet_v2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends BusinessAbstractException {

    public UserNotFoundException(String key) {
        super("user.not.found: with key : " + key);
        log.error("no.user.found: with  key : {}", key);
    }
}
