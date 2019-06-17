package com.ercancelik.redis.pubsub.service;

import com.ercancelik.redis.pubsub.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DemoMessageListener implements MessageListener {

    private final ObjectMapper objectMapper;

    public DemoMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        String json = body.substring(body.lastIndexOf('{'));

        MessageDto msg = null;
        try {
            msg = objectMapper.readValue(json, MessageDto.class);
        } catch (IOException e) {
            log.error("Couldn't convert json", e);
        }
        log.info("Channel: {}, Message: {}", new String(message.getChannel()), msg.getBody());
    }
}
