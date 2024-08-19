package me.sepehrasadiyan.wallet_v2.services.command.common;

import java.math.BigDecimal;

public record CommandResource<T>(T requestBody, String actionName, String accountNumber, BigDecimal amount) {
}
