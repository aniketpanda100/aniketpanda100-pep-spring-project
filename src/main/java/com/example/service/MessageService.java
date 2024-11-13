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

    public List<Message> getMessagesByAccount(int id) {
        return messageRepository.findMessagesByPostedBy(id);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message createMessage(Message message) {
       return messageRepository.save(message);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
     }

    public void deleteMessageById(int id) {
        messageRepository.deleteById(id);
    }
}
