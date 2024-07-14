package me.sepehrasadiyan.wallet_v2.repository;

import me.sepehrasadiyan.wallet_v2.domain.SimpleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleAccountRepository extends JpaRepository<SimpleAccount, Long> {

}
