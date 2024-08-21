package me.sepehrasadiyan.wallet_v2.services.command.impl;

import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import me.sepehrasadiyan.wallet_v2.services.internal.WriterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "createAccountCommand")
public class AccountImpl implements Command {

    private WriterService writerService;

    public AccountImpl(WriterService writerService) {
        this.writerService = writerService;
    }

    @Override
    @Transactional
    public CommandResult execute(final CommandResource commandResource) {
        return writerService.createAccount(commandResource);
    }
}
