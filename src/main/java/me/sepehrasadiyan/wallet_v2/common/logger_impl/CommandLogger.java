package me.sepehrasadiyan.wallet_v2.common.logger_impl;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;

@Slf4j
public class CommandLogger implements CommandObserver {

    @Override
    public void update(CommandResource commandResource, String message) {
        log.info("Executing proxy command: {}", commandResource);
        log.info(message, commandResource.accountNumber(), commandResource.actionName(), commandResource.amount());
    }
}
