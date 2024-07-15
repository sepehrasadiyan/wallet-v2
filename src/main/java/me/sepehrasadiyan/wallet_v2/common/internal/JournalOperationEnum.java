package me.sepehrasadiyan.wallet_v2.common.internal;

import lombok.Getter;

@Getter
public enum JournalOperationEnum {
    DEPOSIT(1),
    WITHDRAW(2);

    private final int value;

    JournalOperationEnum(int value) {
        this.value = value;
    }

    public static JournalOperationEnum fromValue(int value) {
        for (JournalOperationEnum status : JournalOperationEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Override
    public String toString() {
        return name() + "(" + value + ")";
    }
}
