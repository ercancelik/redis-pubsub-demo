package com.ercancelik.redis.pubsub.resource;

import com.ercancelik.redis.pubsub.service.DemoMessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/messages")
@RestController
public class MessageController {

    private final DemoMessagePublisher messagePublisher;

    public MessageController(DemoMessagePublisher messagePublisher){
        this.messagePublisher = messagePublisher;
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody String message){
        this.messagePublisher.publish(message);

        return ResponseEntity.ok(message);
    }
}
