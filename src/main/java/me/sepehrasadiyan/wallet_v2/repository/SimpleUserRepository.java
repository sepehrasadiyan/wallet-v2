package me.sepehrasadiyan.wallet_v2.repository;

import me.sepehrasadiyan.wallet_v2.domain.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {



}
