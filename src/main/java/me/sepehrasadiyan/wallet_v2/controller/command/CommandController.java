package me.sepehrasadiyan.wallet_v2.controller.command;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.request.DepositRequestDto;
import me.sepehrasadiyan.wallet_v2.common.request.WithdrawRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.DepositResponseDto;
import me.sepehrasadiyan.wallet_v2.common.response.WithdrawResponseDto;
import me.sepehrasadiyan.wallet_v2.services.ProxyCommand;
import me.sepehrasadiyan.wallet_v2.services.command.Command;
import me.sepehrasadiyan.wallet_v2.services.command.CommandFactory;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandBuilder;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/command")
@Validated
public class CommandController {

    private final CommandFactory commandFactory;

    @Autowired
    public CommandController(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDto> handelDeposit(@RequestBody @Valid DepositRequestDto request) {
        CommandResource commandResource = new CommandBuilder<>()
                .createDepositCommand(request.getAccountNumber(), request.getAmount())
                .requestDto(request)
                .build();
        ProxyCommand command = commandFactory.getCommand(commandResource.actionName());
        CommandResult commandResult = command.execute(new CommandBuilder().requestDto(request).createDepositCommand(
                request.getAccountNumber(), request.getAmount()).build());
        return ResponseEntity.ok(new DepositResponseDto(commandResult.getReferenceId()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponseDto> handelWithdraw(@RequestBody @Valid WithdrawRequestDto request) {
        CommandResource commandResource = new CommandBuilder<>()
                .createWithdrawalCommand(request.getAccountNumber(), request.getAmount())
                .requestDto(request)
                .build();
        ProxyCommand command = commandFactory.getCommand(commandResource.actionName());
        CommandResult commandResult = command.execute(new CommandBuilder().requestDto(request).createWithdrawalCommand(
                request.getAccountNumber(), request.getAmount()
        ).build());
        return ResponseEntity.ok(new WithdrawResponseDto(commandResult.getReferenceId()));
    }

}