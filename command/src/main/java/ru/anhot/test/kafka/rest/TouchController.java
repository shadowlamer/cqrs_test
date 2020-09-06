package ru.anhot.test.kafka.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.anhot.test.kafka.domain.Touch;
import ru.anhot.test.kafka.service.CommandService;

import java.util.Date;
import java.util.UUID;

@RestController
public class TouchController {

    private final CommandService service;

    public TouchController(CommandService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> touch() {
        return touchWithUuid(UUID.randomUUID());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<String> touchWithUuid(@PathVariable UUID uuid) {
        Touch command = new Touch(new Date().toString(), uuid);
        service.handle(command);
        return ResponseEntity.ok("Command was sent. UUID: " + command.getUuid());
    }
}
