package me.sepehrasadiyan.wallet_v2.common.internal;

import lombok.Getter;

@Getter
public enum SequenceNameEnum {
    REFERENCE_NUMBER("reference_number_seq");

    private final String value;

    SequenceNameEnum(String value) {
        this.value = value;
    }

    public static SequenceNameEnum fromValue(String value) {
        for (SequenceNameEnum status : SequenceNameEnum.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }


}
