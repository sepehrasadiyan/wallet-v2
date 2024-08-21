package me.sepehrasadiyan.wallet_v2.services.internal;

import me.sepehrasadiyan.wallet_v2.common.request.CreateAccountRequestDto;
import me.sepehrasadiyan.wallet_v2.common.response.CreateAccountResponseDto;
import me.sepehrasadiyan.wallet_v2.domain.SimpleAccount;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResource;
import me.sepehrasadiyan.wallet_v2.services.command.common.CommandResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterService {

    private SimpleUserService simpleUserService;
    private SimpleAccountService simpleAccountService;

    public WriterService(SimpleUserService simpleUserService, SimpleAccountService simpleAccountService) {
        this.simpleUserService = simpleUserService;
        this.simpleAccountService = simpleAccountService;
    }

    @Transactional
    public CommandResult createAccount(CommandResource commandResource) {
        //todo: you can use Lock interface if you have obsess in synchronization
        //      version in Entity handel everyThing almost


        CreateAccountRequestDto createAccountResponseDto = (CreateAccountRequestDto) commandResource.requestBody();
        SimpleUser simpleUser = simpleUserService.createUser(createAccountResponseDto);
        SimpleAccount simpleAccount = simpleAccountService.createAccount(simpleUser, createAccountResponseDto);
        return new CommandResult(null, new CreateAccountResponseDto(simpleAccount.getAccountNumber()));
    }
}
