package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null) return ResponseEntity.ok().build();
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

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

}
