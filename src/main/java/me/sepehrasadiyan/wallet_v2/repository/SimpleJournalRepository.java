package me.sepehrasadiyan.wallet_v2.repository;

import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SimpleJournalRepository extends JpaRepository<SimpleJournal, Long> {
    @Query("SELECT s.id FROM SimpleJournal s")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    List<Long> findAllIds();

    @Query("SELECT s FROM SimpleJournal s WHERE s.id IN :ids")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    List<SimpleJournal> findByIds(@Param("ids") List<Long> ids);
}
