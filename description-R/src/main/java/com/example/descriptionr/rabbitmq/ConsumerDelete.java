package com.example.descriptionr.rabbitmq;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.model.DescriptionDTO;
import com.example.descriptionr.service.ReadingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerDelete {
    private final ReadingService readingService;
    private final MessageConverter jsonMessageConverter;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public ConsumerDelete(ReadingService readingService, MessageConverter jsonMessageConverter, ObjectMapper objectMapper, ModelMapper modelMapper){
        this.readingService = readingService;
        this.jsonMessageConverter = jsonMessageConverter;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    @RabbitListener(queues = "description-delete")
    public void receiveDescription(String message){
        try {
            DescriptionDTO dto = objectMapper.readValue(message, DescriptionDTO.class);
            Description descr = modelMapper.map(dto, Description.class);
            readingService.deleteByTitle(descr.getTitle());
            System.out.println("Received message for deletion: " + dto.toString());
        } catch (IOException e) {
            System.err.println("Error deserializing message: " + e.getMessage());
        }
    }
}
