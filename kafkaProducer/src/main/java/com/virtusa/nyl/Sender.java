package com.virtusa.nyl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;

public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        //LOGGER.info("Sending topic='{}' and payload='{}'", topic, payload);
        kafkaTemplate.send(topic, payload);
    }

}
