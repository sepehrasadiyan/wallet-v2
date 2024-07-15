package me.sepehrasadiyan.wallet_v2.common.internal;

import lombok.Getter;

@Getter
public enum AccountTypeEnum {
    PERSONAL(1);

    private final int value;

    AccountTypeEnum(int value) {
        this.value = value;
    }

    public static AccountTypeEnum fromValue(int value) {
        for (AccountTypeEnum status : AccountTypeEnum.values()) {
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
