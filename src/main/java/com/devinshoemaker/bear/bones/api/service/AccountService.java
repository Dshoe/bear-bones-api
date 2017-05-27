package com.devinshoemaker.bear.bones.api.service;

import com.devinshoemaker.bear.bones.api.domain.Account;
import com.devinshoemaker.bear.bones.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Business logic for accounts.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@Component
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Create a new account.
     *
     * @param account A new account object to be created.
     * @return The created account.
     */
    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    /**
     * Get an account by it's ID.
     *
     * @param id The ID of the account.
     * @return The account for the given ID.
     */
    public Account findAccountById(final int id) {
        return accountRepository.findOne(id);
    }

    /**
     * Update an account by it's ID.
     *
     * @param id      The ID of the account.
     * @param account The modified account.
     * @return The updated account.
     */
    public Account updateAccountById(final int id, final Account account) {
        if (accountRepository.findOne(id) == null) {
            throw new IllegalArgumentException("No account found with ID: " + id);
        }
        account.setId(id);
        return accountRepository.save(account);
    }

    /**
     * Delete an account by it's ID.
     *
     * @param id The ID of the account to be deleted.
     */
    public void deleteAccountById(final int id) {
        accountRepository.delete(id);
    }

}
