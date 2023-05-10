package com.example.descriptionr.rabbitmq;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.model.DescriptionDTO;
import com.example.descriptionr.service.ReadingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {
    private final ReadingService readingService;
    private final MessageConverter jsonMessageConverter;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public ConsumerService(ReadingService readingService, MessageConverter jsonMessageConverter, ObjectMapper objectMapper, ModelMapper modelMapper){
        this.readingService = readingService;
        this.jsonMessageConverter = jsonMessageConverter;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    @RabbitListener(queues = {"${queue.name}"})
    public void receiveDescription(String message){
        try {
            DescriptionDTO dto = objectMapper.readValue(message, DescriptionDTO.class);
            Description descr = modelMapper.map(dto, Description.class);
            readingService.create(descr);
            System.out.println("Received message: " + dto.toString());
        } catch (IOException e) {
            System.err.println("Error deserializing message: " + e.getMessage());
        }
    }
}
