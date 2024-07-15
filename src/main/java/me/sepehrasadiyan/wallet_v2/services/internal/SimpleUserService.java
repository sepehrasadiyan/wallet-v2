package me.sepehrasadiyan.wallet_v2.services.internal;

import me.sepehrasadiyan.wallet_v2.common.internal.SimpleUserStatusEnum;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import me.sepehrasadiyan.wallet_v2.exception.UserNotFoundException;
import me.sepehrasadiyan.wallet_v2.exception.UserStatusException;
import me.sepehrasadiyan.wallet_v2.repository.SimpleUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleUserService {

    private final SimpleUserRepository simpleUserRepository;

    public SimpleUserService(SimpleUserRepository simpleUserRepository) {
        this.simpleUserRepository = simpleUserRepository;
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
