package me.sepehrasadiyan.wallet_v2.common.logger_impl;

import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;

public interface CommandObserver {
    void update(CommandResource commandResource, String message);
}
