package ru.anhot.test.kafka.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.anhot.test.kafka.domain.TouchProjection;
import ru.anhot.test.kafka.domain.TouchSummary;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class TouchController {

    private final TouchProjection projection;

    public TouchController(TouchProjection projection) {
        this.projection = projection;
    }

    @GetMapping("/touches")
    public ResponseEntity<List<TouchSummary>> getTouches() {
        return ResponseEntity.ok(
                projection.getAllSummaries().entrySet().stream()
                        .map(Map.Entry::getValue).collect(Collectors.toList())
        );
    }

    @GetMapping("/touch/{uuid}")
    public ResponseEntity<TouchSummary> getTouchByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(projection.getSummaryByUuid(uuid));
    }
}
