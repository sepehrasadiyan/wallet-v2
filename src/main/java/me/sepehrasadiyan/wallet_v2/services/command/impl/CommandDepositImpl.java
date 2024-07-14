package me.sepehrasadiyan.wallet_v2.services.command.impl;

import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import org.springframework.stereotype.Service;

@Service(value = "depositCommand")
public class CommandDepositImpl implements Command {

    @Override
    public CommandResult execute(CommandResource commandResource) {
        return null;
    }
}
