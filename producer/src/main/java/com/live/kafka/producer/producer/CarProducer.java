package com.live.kafka.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.live.kafka.producer.DTO.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarProducer {
    private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);
    private final String topic;
    private final KafkaTemplate<String, CarDTO> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CarDTO carDTO) {
        try {
            String json = objectMapper.writeValueAsString(carDTO);
            kafkaTemplate.send("cars", carDTO);
            logger.info("Mensagem enviada com sucesso: {}", json);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter objeto para JSON", e);
        }
    }
}
