package me.sepehrasadiyan.wallet_v2.services.internal;

import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import me.sepehrasadiyan.wallet_v2.repository.SimpleJournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SimpleJournalService {

    private final SimpleJournalRepository simpleJournalRepository;

    public SimpleJournalService(SimpleJournalRepository simpleJournalRepository) {
        this.simpleJournalRepository = simpleJournalRepository;
    }

    public List<Long> getIdsAfter(LocalDateTime startTime) {
        return simpleJournalRepository.findAllIds();
    }

    public List<SimpleJournal> getJournalsWithIds(List<Long> ids) {
        return simpleJournalRepository.findByIds(ids);
    }
}
