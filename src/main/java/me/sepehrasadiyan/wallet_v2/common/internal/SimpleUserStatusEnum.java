package me.sepehrasadiyan.wallet_v2.common.internal;

import lombok.Getter;

@Getter
public enum SimpleUserStatusEnum {
    ACTIVE(1),
    SUSPEND(2),
    DEACTIVATE(3);

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

    @Override
    public String toString() {
        return name() + "(" + value + ")";
    }
}
