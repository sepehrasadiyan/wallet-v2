package me.sepehrasadiyan.wallet_v2.services.command.common;


import lombok.Getter;

@Getter
public class CommandBuilder<T>  {

    private T requestBody;

    private String actionName;

    public CommandResource<T> build() {
        return new CommandResource<>(requestBody, actionName);
    }

    public CommandBuilder<T> requestDto(final T requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CommandBuilder<T> createDepositCommand() {
        this.actionName = "deposit";
        return this;
    }

}
