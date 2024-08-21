package me.sepehrasadiyan.wallet_v2.services.command;

import jakarta.annotation.Resource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;

@Resource
public interface Command {

    CommandResult execute(final CommandResource commandResource);
}
