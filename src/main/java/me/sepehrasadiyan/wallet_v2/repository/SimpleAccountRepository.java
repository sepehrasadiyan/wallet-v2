package me.sepehrasadiyan.wallet_v2.repository;

import me.sepehrasadiyan.wallet_v2.domain.SimpleAccount;
import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleAccountRepository extends JpaRepository<SimpleAccount, Long> {
    List<SimpleAccount> findAllByUser(SimpleUser user);
}
