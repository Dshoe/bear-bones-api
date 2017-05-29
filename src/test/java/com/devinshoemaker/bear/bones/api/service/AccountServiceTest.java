package com.devinshoemaker.bear.bones.api.service;

import com.devinshoemaker.bear.bones.api.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * AccountService unit tests.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void createActiveAccount() throws Exception {
        Account newAccount = buildDefaultAccount();

        Account createdAccount = accountService.createAccount(newAccount);

        assertAccountCreated(createdAccount, newAccount);

        accountService.deleteAccountById(createdAccount.getId());
    }

    @Test
    public void createInactiveAccount() throws Exception {
        Account newAccount = new Account();
        newAccount.setName(UUID.randomUUID().toString());
        newAccount.setActive(false);

        Account createdAccount = accountService.createAccount(newAccount);

        assertAccountCreated(createdAccount, newAccount);

        accountService.deleteAccountById(createdAccount.getId());
    }

    @Test
    public void createActiveNotSetAccount() throws Exception {
        Account account = new Account();
        account.setName(UUID.randomUUID().toString());

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        assertNotNull(createdAccount.getId());
        assertNotNull(createdAccount.getName());
        assertEquals(createdAccount.getName(), account.getName());
        assertTrue(createdAccount.isActive());

        accountService.deleteAccountById(createdAccount.getId());
    }

    @Test
    public void createEmptyAccount() throws Exception {
        try {
            accountService.createAccount(new Account());
            assertTrue(false); // Fail the test if an exception is not thrown
        } catch (TransactionSystemException e) {
            // Do nothing
        }
    }

    @Test
    public void findAccountById() throws Exception {
        Account newAccount = buildDefaultAccount();

        Account createdAccount = accountService.createAccount(newAccount);

        Account retrievedAccount = accountService.findAccountById(createdAccount.getId());

        assertAccountEquals(retrievedAccount, createdAccount);

        accountService.deleteAccountById(retrievedAccount.getId());
    }

    @Test
    public void updateAccountById() throws Exception {
        Account newAccount = buildDefaultAccount();

        Account createdAccount = accountService.createAccount(newAccount);
        createdAccount.setName(UUID.randomUUID().toString());
        createdAccount.setActive(false);

        Account updatedAccount = accountService.updateAccountById(createdAccount.getId(), createdAccount);

        assertAccountCreated(updatedAccount, createdAccount);
    }



    @Test
    public void deleteAccountById() throws Exception {
    }

    private Account buildDefaultAccount() {
        Account account = new Account();
        account.setName(UUID.randomUUID().toString());
        account.setActive(true);

        return account;
    }

    private void assertAccountCreated(Account createdAccount, Account newAccount) {
        assertNotNull(createdAccount);
        assertNotNull(createdAccount.getId());
        assertNotNull(createdAccount.getName());
        assertEquals(createdAccount.getName(), newAccount.getName());
        assertEquals(createdAccount.isActive(), newAccount.isActive());
    }

    private void assertAccountEquals(Account account1, Account account2) {
        assertNotNull(account1);
        assertNotNull(account1.getId());
        assertNotNull(account1.getName());
        assertEquals(account1.getId(), account2.getId());
        assertEquals(account1.getName(), account2.getName());
        assertEquals(account1.isActive(), account2.isActive());
    }

}