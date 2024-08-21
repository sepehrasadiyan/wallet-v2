package me.sepehrasadiyan.wallet_v2.common.logger_impl;

import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;

import java.util.ArrayList;
import java.util.List;

public class CommandSubject {
    private final List<CommandObserver> observers = new ArrayList<>();

    public void registerObserver(CommandObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(CommandObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(CommandResource commandResource, String message) {
        for (CommandObserver observer : observers) {
            observer.update(commandResource, message);
        }
    }
}
