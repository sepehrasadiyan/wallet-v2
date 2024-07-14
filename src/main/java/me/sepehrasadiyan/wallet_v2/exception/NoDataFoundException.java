package me.sepehrasadiyan.wallet_v2.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoDataFoundException extends BusinessAbstractException {
    public NoDataFoundException(String key) {
        super("data.not.found: with key : " + key);
        log.error("no.data.found: with key : {}", key);
    }

}
