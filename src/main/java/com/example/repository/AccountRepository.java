package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repository interface for accounts using JPA
 */
public interface AccountRepository extends JpaRepository<Account, Integer>{

    /*
     * Gets account by its username
     * @param username String with unique username of account
     * @return the account (null if not found)
     */
    Account findAccountByUsername(String username);

    /*
     * Gets account by its id
     * @param id int id
     * @return the account (null if not found)
     */
    Account findAccountByAccountId(int id);

}
