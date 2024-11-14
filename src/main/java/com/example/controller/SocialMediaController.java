package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * Contains endpoints and handlers for the controller using Spring. The endpoint descriptions can be
 * found in readme.md as well as the test cases. The @GET/POST/PUT/DELETE/etc Mapping annotations
 * are used where applicable as well as the @ResponseBody and @PathVariable annotations.
 */
@RestController
public class SocialMediaController {
    /*
     * Service instance variables for messages and accounts
     */
    MessageService messageService;
    AccountService accountService;

    /*
     * Constructor to instantiate service variables
     */
    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    /*
     * Gets messages by their id
     * @param messageId int id of the message
     * @return ResponseEntity with message in its body if found
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) return ResponseEntity.ok().build();
        return ResponseEntity.status(200).body(message);
    }

    /*
     * Gets all messages
     * @return ResponseEntity with list of messages in body
     */
    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    /*
     * Gets all message by a user
     * @param accountId int id of user
     * @return ResponseEntity with list of messages in body
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getMessagesByAccount(@PathVariable int accountId){
        List<Message> messages = messageService.getMessagesByAccount(accountId);
        return ResponseEntity.status(200).body(messages);
    }

    /*
     * Authenticates user login attempt
     * @param account an Account with username/password but no id
     * @return ResponseEntity holding account with id if found/authenticated
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account){
        Account retrievedAccount = accountService.getAccountByUsername(account.getUsername());
        if (retrievedAccount == null) return ResponseEntity.status(401).build();
        if (!retrievedAccount.getUsername().equals(account.getUsername())) {
            return ResponseEntity.status(401).build();
        }
        if (!retrievedAccount.getPassword().equals(account.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.status(200).body(retrievedAccount);
    }

    /*
     * Register a user
     * @param account an Account to post
     * @return ResponseEntity with posted account if successful
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account account){
        Account duplicateAccount = accountService.getAccountByUsername(account.getUsername());
        if (duplicateAccount != null) return ResponseEntity.status(409).build();
        if (account.getUsername().length() < 1 || account.getPassword().length() < 4) {
            return ResponseEntity.status(400).build();
        }
        Account retrievedAccount = accountService.createAccount(account);
        return ResponseEntity.status(200).body(retrievedAccount);
    }

    /*
     * Posts new message
     * @param message Message to post
     * @return ResponseEntity with persisted message if successful
     */
    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message){
        if (message.getMessageText().length() < 1 || message.getMessageText().length() > 255) {
            return ResponseEntity.status(400).build();
        }
        Account account = accountService.getAccountById(message.getPostedBy());
        if (account == null) return ResponseEntity.status(400).build();
        Message retrievedMessage = messageService.createMessage(message);
        return ResponseEntity.status(200).body(retrievedMessage);
    }

    /*
     * Updates message's text, identified by id
     * @param messageId int id of the message
     * @param messageText new text for the message
     * @return ResponseEntity with updated message if successful
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessageById(@PathVariable int messageId, @RequestBody String messageText){
        messageText = messageText.substring(18,messageText.length()-1);
        if (messageText.length() < 1 || messageText.length() > 255) {
            return ResponseEntity.status(400).build();
        }
        Message message = messageService.getMessageById(messageId);
        if (message == null) return ResponseEntity.status(400).build();
        message.setMessageText(messageText);
        messageService.updateMessage(message);
        return ResponseEntity.ok().body(1);
    }

    /*
     * Deletes message identified by id
     * @param messageId int id of the message
     * @return ResponseEntity with 1 in body if successful
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) return ResponseEntity.ok().build();
        messageService.deleteMessageById(messageId);
        return ResponseEntity.ok().body(1);
    }

}
