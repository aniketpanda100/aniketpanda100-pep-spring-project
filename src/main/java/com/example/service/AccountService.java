package com.example.service;

import com.example.entity.*;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    public Account createAccount(Account account) {
        accountRepository.save(account);
        return accountRepository.findAccountByUsername(account.getUsername());
    }
}
