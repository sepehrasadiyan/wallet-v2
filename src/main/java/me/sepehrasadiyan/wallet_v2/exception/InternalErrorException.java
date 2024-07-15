package me.sepehrasadiyan.wallet_v2.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalErrorException extends BusinessAbstractException {
    public InternalErrorException(String message) {
        super("unknown.exception : with message : " + message);
        log.error("unknown.exception : with message : {}", message);
    }
}
