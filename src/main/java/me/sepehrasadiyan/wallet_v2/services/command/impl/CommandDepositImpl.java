package me.sepehrasadiyan.wallet_v2.services.command.impl;

import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleAccountService;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleJournalService;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleUserService;
import org.springframework.stereotype.Service;

@Service(value = "depositCommand")
public class CommandDepositImpl implements Command {

    private final SimpleUserService simpleUserService;

    private final SimpleAccountService simpleAccountService;

    private final SimpleJournalService simpleJournalService;

    public CommandDepositImpl(SimpleUserService simpleUserService, SimpleAccountService simpleAccountService, SimpleJournalService simpleJournalService) {
        this.simpleUserService = simpleUserService;
        this.simpleAccountService = simpleAccountService;
        this.simpleJournalService = simpleJournalService;
    }

    @Override
    public CommandResult execute(CommandResource commandResource) {
        return null;
    }
}
