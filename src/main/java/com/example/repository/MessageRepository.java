package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/*
 * Repository interface for messages using JPA
 */
public interface MessageRepository extends JpaRepository<Message, Integer>{

    /*
     * Gets message by its id
     * @param id int id of the message
     * @return the message (null if not found)
     */
    Message findMessageByMessageId(int id);

    /*
     * Gets all messages belonging to an account
     * @param id int id of the account
     * @return list of messages (empty if none found)
     */
    List<Message> findMessagesByPostedBy(int id);

}
