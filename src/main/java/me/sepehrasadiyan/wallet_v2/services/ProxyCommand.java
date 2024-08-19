package me.sepehrasadiyan.wallet_v2.services;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;


@Slf4j
public class ProxyCommand implements Command {

    private final Command command;

    public ProxyCommand(Command command) {
        this.command = command;
    }

    public ProxyCommand() {
        throw new IllegalArgumentException("ProxyCommand cannot be constructed");
    }

    @Override
    public CommandResult execute(CommandResource commandResource) {
        log.info("Executing proxy command: {}", commandResource);
        log.info("perform command with accountNumber :{} and transactionType{} and amount :{}",
                commandResource.accountNumber(), commandResource.actionName(), commandResource.amount());
        final CommandResult commandResult = command.execute(commandResource);
        log.info("done command with accountNumber :{} and transactionType{} and amount :{}",
                commandResource.accountNumber(), commandResource.actionName(), commandResource.amount());
        return commandResult;
    }

}
