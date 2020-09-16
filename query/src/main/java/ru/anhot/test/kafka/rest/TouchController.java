package ru.anhot.test.kafka.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.anhot.test.kafka.handler.TouchProjection;

import java.util.Map;
import java.util.UUID;

@RestController
public class TouchController {

    private final TouchProjection projection;

    public TouchController(TouchProjection projection) {
        this.projection = projection;
    }

    @GetMapping("/touches")
    public ResponseEntity<Map<UUID, String>> getTouches() {
        return ResponseEntity.ok(projection.getAllData());
    }

    @GetMapping("/touch/{uuid}")
    public ResponseEntity<String> getTouchByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(projection.getDataByUuid(uuid));
    }

}
