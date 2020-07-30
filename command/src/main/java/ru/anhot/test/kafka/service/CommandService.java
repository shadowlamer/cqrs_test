package ru.anhot.test.kafka.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import ru.anhot.test.kafka.domain.Touch;

@Service
public class CommandService {

    private final CommandGateway commandGateway;

    public CommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void handle(Touch command) {
        commandGateway.send(command);
    }
}
