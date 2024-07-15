package me.sepehrasadiyan.wallet_v2.common.internal;

import lombok.Getter;

@Getter
public enum SimpleUserStatusEnum {
    ACTIVE(0),
    SUSPEND(1),
    DEACTIVATE(2);

    private final int value;

    SimpleUserStatusEnum(int value) {
        this.value = value;
    }


    public static SimpleUserStatusEnum fromValue(int value) {
        for (SimpleUserStatusEnum status : SimpleUserStatusEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
