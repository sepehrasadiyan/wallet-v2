package me.sepehrasadiyan.wallet_v2.services.internal;

import me.sepehrasadiyan.wallet_v2.common.internal.SimpleUserStatusEnum;
import me.sepehrasadiyan.wallet_v2.common.request.CreateAccountRequestDto;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.exception.UserNotFoundException;
import me.sepehrasadiyan.wallet_v2.exception.UserStatusException;
import me.sepehrasadiyan.wallet_v2.repository.SimpleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SimpleUserService {

    private final SimpleUserRepository simpleUserRepository;

    public SimpleUserService(SimpleUserRepository simpleUserRepository) {
        this.simpleUserRepository = simpleUserRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public SimpleUser createUser(final CreateAccountRequestDto createAccountRequestDto) {
        //todo: you can use Lock interface if you have obsess in synchronization
        //      version in Entity handel everyThing almost
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setStatus(SimpleUserStatusEnum.ACTIVE);
        simpleUser.setUsername(createAccountRequestDto.getName() + createAccountRequestDto.getLastname());
        return simpleUserRepository.saveAndFlush(simpleUser);
    }

    public SimpleUser getUserForProcessCommand(Long userId) {
        Optional<SimpleUser> simpleUser = getSimpleUser(userId);
        if (simpleUser.isPresent()) {
            if(simpleUser.get().getStatus().equals(SimpleUserStatusEnum.ACTIVE)) {
                return simpleUser.get();
            } else {
                throw new UserStatusException("user is not enabled");
            }
        } else {
            throw new UserNotFoundException(String.valueOf(userId));
        }
    }

    public SimpleUser getUserForProcessQuery(Long userId) {
        Optional<SimpleUser> simpleUser = getSimpleUser(userId);
        if (simpleUser.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        } else {
            return simpleUser.get();
        }
    }

    private Optional<SimpleUser> getSimpleUser(Long userId) {
        return simpleUserRepository.findById(userId);
    }
}
