package me.sepehrasadiyan.wallet_v2.services.command.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommandResult implements Serializable {

    private Long referenceId;

}
