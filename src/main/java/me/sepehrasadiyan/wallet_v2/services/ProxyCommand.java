package me.sepehrasadiyan.wallet_v2.services;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.logger_impl.CommandLogger;
import me.sepehrasadiyan.wallet_v2.common.logger_impl.CommandSubject;
import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;


@Slf4j
public class ProxyCommand implements Command {

    private final Command command;
    private final CommandSubject commandSubject = new CommandSubject();

    public ProxyCommand(Command command) {
        this.command = command;
        commandSubject.registerObserver(new CommandLogger());
    }

    public ProxyCommand() {
        throw new IllegalArgumentException("ProxyCommand cannot be constructed");
    }

    @Override
    public CommandResult execute(CommandResource commandResource) {
        commandSubject.notifyObservers(commandResource,
                "Performing command with accountNumber: {} and transactionType: {} and amount: {}");
        final CommandResult commandResult = command.execute(commandResource);
        commandSubject.notifyObservers(commandResource,
                "Done command with accountNumber: {} and transactionType: {} and amount: {}");
        return commandResult;
    }

}
