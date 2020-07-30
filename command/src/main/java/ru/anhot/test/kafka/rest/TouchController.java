package ru.anhot.test.kafka.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anhot.test.kafka.domain.Touch;
import ru.anhot.test.kafka.service.CommandService;

@RestController
public class TouchController {

    private final CommandService service;

    public TouchController(CommandService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> touch() {
        Touch command = new Touch("Hello!");
        service.handle(command);
        return ResponseEntity.ok("Command was sent. UUID: " + command.getUuid());
    }
}
