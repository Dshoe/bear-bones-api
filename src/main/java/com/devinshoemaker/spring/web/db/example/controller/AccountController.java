package com.devinshoemaker.spring.web.db.example.controller;

import com.devinshoemaker.spring.web.db.example.domain.Account;
import com.devinshoemaker.spring.web.db.example.repository.AccountRepository;
import com.devinshoemaker.spring.web.db.example.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
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
        Errors errors = new BeanPropertyBindingResult(account, account.getClass().getName());
        AccountValidator accountValidator = new AccountValidator();
        accountValidator.validate(account, errors, false);
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.CREATED);
    }

}
