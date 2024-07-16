package me.sepehrasadiyan.wallet_v2.services.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sepehrasadiyan.wallet_v2.domain.SimpleJournal;
import me.sepehrasadiyan.wallet_v2.services.internal.SimpleJournalService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
@Slf4j
public class LedgerSchedule {


    private final SimpleJournalService simpleJournalService;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    private final AtomicReference<BigDecimal> totalAmountChange = new AtomicReference<>(BigDecimal.ZERO);

    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Tehran")
    public void reportJournalIds() {
        executorService.execute(this::run);
    }

    public void run() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(24);
        List<Long> allIds = simpleJournalService.getIdsAfter(startTime);

        // Process IDs in batches of 200
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < allIds.size(); i += 200) {
            int end = Math.min(i + 200, allIds.size());
            List<Long> batch = allIds.subList(i, end);

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> processBatch(batch), executorService);
            futures.add(future);
        }
        for (CompletableFuture<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error processing batch: {}", e.getMessage());
            }
        }
        log.info("Total amount_change from the last 24 hours: {}", totalAmountChange.get());
    }

    private void processBatch(List<Long> batch) {
        try {
            List<SimpleJournal> journals = simpleJournalService.getJournalsWithIds(batch);
            BigDecimal batchTotal = journals.stream()
                    .map(SimpleJournal::getAmount_change)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            log.info("The batchPrice {}", batchTotal);
            BigDecimal bigDecimal = totalAmountChange.updateAndGet(current -> current.add(batchTotal));
            log.info("after update TotalPrice {}", bigDecimal);
        } catch (Exception e) {
            log.error("Failed to process batch: {}", e.getMessage());
        }
    }
}
