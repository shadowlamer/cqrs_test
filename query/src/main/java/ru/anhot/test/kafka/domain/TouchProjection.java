package ru.anhot.test.kafka.domain;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;
import ru.anhot.test.kafka.repo.TouchRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@ProcessingGroup("query")
@Slf4j
public class TouchProjection {

    private Map<UUID, TouchSummary> summaries = new HashMap<>();
    private TouchRepo repo;

    public TouchProjection(TouchRepo repo) {
        this.repo = repo;
        log.info("TouchProjection created");
        Iterable<TouchSummary> touchSummaries = repo.findAll();
        touchSummaries.forEach(touchSummary -> {
            summaries.put(touchSummary.getUuid(), touchSummary);
            log.info("State fetched from database: " + touchSummary);
        });
    }

    @EventSourcingHandler
    public void handleEvent(Touched event) {
        log.info("Touch event handled. Data changed to " +
                event.getData() + " for uuid " + event.getUuid());
        TouchSummary summary = new TouchSummary(event.getUuid(), event.getData());
        repo.save(summary);
        log.info("State saved to database: " + summary);
        summaries.put(event.getUuid(), new TouchSummary(event.getUuid(), event.getData()));
    }

    public TouchSummary getSummaryByUuid(UUID uuid) {
        return summaries.get(uuid);
    }

    public Map<UUID, TouchSummary> getAllSummaries() {
        return summaries;
    }

    public long getSummariesCount() {
        return summaries.size();
    }
}
