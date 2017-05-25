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
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
    }

    /**
     * Get an account by it's ID.
     *
     * @param id The ID of the account.
     * @return The account for the given ID.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findById(@PathVariable int id) {
        return new ResponseEntity<>(accountRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Update an account by it's ID.
     *
     * @param id      The ID of the account.
     * @param account The updated account.
     * @return The updated account.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateById(@PathVariable int id, @RequestBody @Valid Account account) {
        account.setId(id);
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    /**
     * Delete an account by it's ID.
     *
     * @param id The ID of the account.
     * @return Response code 200.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable int id) {
        accountRepository.delete(id);
        return HttpStatus.OK;
    }

}
