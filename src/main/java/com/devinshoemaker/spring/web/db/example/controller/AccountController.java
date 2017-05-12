package com.devinshoemaker.spring.web.db.example.controller;

import com.devinshoemaker.spring.web.db.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
