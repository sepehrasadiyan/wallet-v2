package me.sepehrasadiyan.wallet_v2.services.command.common;

public record CommandResource<T>(T requestBody, String actionName) {
}
