package me.sepehrasadiyan.wallet_v2.services.command;

import me.sepehrasadiyan.wallet_v2.services.ProxyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final ApplicationContext context;

    @Autowired
    public CommandFactory(ApplicationContext context) {
        this.context = context;
    }

    public Command getCommand(String commandType) {
        String beanName = commandType + "Command";
        if (!context.containsBean(beanName)) {
            throw new IllegalArgumentException("Invalid command type: " + commandType);
        }
        Command command = (Command) context.getBean(beanName);
        return new ProxyCommand(command);
    }
}