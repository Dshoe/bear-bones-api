package com.devinshoemaker.spring.web.db.example.controller;

import com.devinshoemaker.spring.web.db.example.domain.Account;
import com.devinshoemaker.spring.web.db.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * CRUD operations for the user table.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Create a new account.
     *
     * @param account A new account object to be created.
     * @return The created account record.
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
    }

    /**
     * Get an account by it's ID.
     *
     * @param id The ID of the account.
     * @return The account for the given ID.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findById(@PathVariable int id) {
        return new ResponseEntity<>(accountRepository.findOne(id), HttpStatus.OK);
    }

}
