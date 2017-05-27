package com.devinshoemaker.bear.bones.api.controller;

import com.devinshoemaker.bear.bones.api.domain.Account;
import com.devinshoemaker.bear.bones.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * API end points for accounts.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create a new account.
     *
     * @param account A new account object to be created.
     * @return The created account.
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody @Valid Account account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    /**
     * Get an account by it's ID.
     *
     * @param id The ID of the account.
     * @return The account for the given ID.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccountById(@PathVariable int id) {
        return new ResponseEntity<>(accountService.findAccountById(id), HttpStatus.OK);
    }

    /**
     * Update an account by it's ID.
     *
     * @param id      The ID of the account.
     * @param account The modified account.
     * @return The updated account.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccountById(@PathVariable int id, @RequestBody @Valid Account account) {
        return new ResponseEntity<>(accountService.updateAccountById(id, account), HttpStatus.OK);
    }

    /**
     * Delete an account by it's ID.
     *
     * @param id The ID of the account to be deleted.
     * @return Response code 200.
     */
    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteAccountById(@PathVariable int id) {
        accountService.deleteAccountById(id);
        return HttpStatus.OK;
    }

}
