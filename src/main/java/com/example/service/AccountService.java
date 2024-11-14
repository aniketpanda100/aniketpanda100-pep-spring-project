package com.example.service;

import com.example.entity.*;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Service class for Accounts, handles business logic, sits between controller class and 
 * account repository class.
 */
@Service
public class AccountService {
    /*
     * Repository instance variable
     */
    AccountRepository accountRepository;

    /*
     * Constructor instantiates repository variable
     */
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /*
     * Gets account by its username
     * @param username String specifying unique username of account
     * @return the account (null if not found)
     */
    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

     /*
     * Gets account by its id
     * @param id int id
     * @return the account (null if not found)
     */
    public Account getAccountById(int id) {
        return accountRepository.findAccountByAccountId(id);
    }

     /*
     * Creates an account
     * @param account an Account to post to database
     * @return the persisted account 
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
