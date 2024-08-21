package me.sepehrasadiyan.wallet_v2.controller.command;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.common.request.CreateAccountRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.CreateAccountResponseDto;
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
@RequestMapping("/api/v1/account")
@Validated
public class AccountController {

    private final CommandFactory commandFactory;

    @Autowired
    public AccountController(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponseDto> handelCreateAccount(@RequestBody @Valid CreateAccountRequestDto request) {
        CommandResource commandResource = new CommandBuilder<>()
                .createAccount()
                .requestDto(request)
                .build();
        ProxyCommand command = commandFactory.getCommand(commandResource.actionName());
        CommandResult commandResult = command.execute(new CommandBuilder().requestDto(request)
                .createAccount()
                .build());
        return ResponseEntity.ok((CreateAccountResponseDto) commandResult.getResponseDto());
    }
}
