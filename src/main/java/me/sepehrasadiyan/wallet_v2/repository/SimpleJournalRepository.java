package me.sepehrasadiyan.wallet_v2.repository;

import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleJournalRepository extends JpaRepository<SimpleJournal, Long> {
}
