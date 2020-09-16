package ru.anhot.test.kafka.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anhot.test.kafka.domain.Touch;
import ru.anhot.test.kafka.service.CommandService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TouchController {

    private final CommandService service;

    public TouchController(CommandService service) {
        this.service = service;
    }

    @PutMapping ("/create_or_update")
    public ResponseEntity<String> touch(@RequestBody(required = false) String uuid) {
        Touch command;
        if (null == uuid) {
            command = new Touch(new Date().toString(), UUID.randomUUID());
        } else {
            try {
                command = new Touch(new Date().toString(), UUID.fromString(uuid));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        service.handle(command);
        return ResponseEntity.ok("Command was sent. UUID: " +
                command.getUuid() + ", data: " + command.getData());
    }
}
