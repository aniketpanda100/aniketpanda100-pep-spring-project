package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message getMessageById(int id) {
        return messageRepository.findMessageByMessageId(id);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
