package me.sepehrasadiyan.wallet_v2.services.command;

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
        return (Command) context.getBean(beanName);
    }
}