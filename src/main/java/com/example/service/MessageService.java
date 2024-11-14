package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
 * Service class for Messages, handles business logic, sits between controller class and 
 * message repository class.
 */
@Service
public class MessageService {
    /*
     * Repository instance variable
     */
    MessageRepository messageRepository;

    /*
     * Constructor instantiates repository variable
     */
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /*
     * Gets message by its id
     * @param id int id of the message
     * @return the message (null if not found)
     */
    public Message getMessageById(int id) {
        return messageRepository.findMessageByMessageId(id);
    }

    /*
     * Gets all messages belonging to an account
     * @param id int id of the account
     * @return list of messages (empty if none found)
     */
    public List<Message> getMessagesByAccount(int id) {
        return messageRepository.findMessagesByPostedBy(id);
    }

    /*
     * Gets all messages
     * @return list of messages (empty if none found)
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /*
     * Creates a new message
     * @param message a Message to post to repository
     * @return persisted message
     */
    public Message createMessage(Message message) {
       return messageRepository.save(message);
    }

    /*
     * Saves an updated message
     * @param message a Message to save to the repository
     * @return persisted message
     */
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
     }

    /*
     * Deletes a message by id
     * @param id int id of the message
     */
    public void deleteMessageById(int id) {
        messageRepository.deleteById(id);
    }
}
