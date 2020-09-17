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
    public ResponseEntity<Touch> touch(@RequestBody(required = false) Touch touch) {
        Touch command;
        if (null == touch) {
            command = new Touch(new Date().toString(), UUID.randomUUID());
        } else {
            command = new Touch(new Date().toString(), touch.getUuid());
        }
        service.handle(command);
        return ResponseEntity.accepted().body(command);
    }
}

