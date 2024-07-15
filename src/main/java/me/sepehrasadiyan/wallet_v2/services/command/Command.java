package me.sepehrasadiyan.wallet_v2.services.command;

import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;

public interface Command {

    CommandResult execute(final CommandResource commandResource);
}
