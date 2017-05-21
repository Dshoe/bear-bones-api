package com.devinshoemaker.spring.web.db.example.controller;

import com.devinshoemaker.spring.web.db.example.domain.Account;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test AccountController methods.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create() throws Exception {
        Account account = new Account();
        account.setName(UUID.randomUUID().toString());
        account.setActive(true);

        Account createdAccount = createNewAccount(account);

        assertNotNull(createdAccount);
        assertNotNull(createdAccount.getId());
        assertNotNull(createdAccount.getName());
        assertEquals(createdAccount.getName(), account.getName());
        assertEquals(createdAccount.isActive(), account.isActive());
    }

    @Test
    public void createEmpty() throws Exception {
        Account account = new Account();
        mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(account))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private Account createNewAccount(Account account) throws Exception {
        Gson gson = new Gson();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(account))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        return gson.fromJson(result.getResponse().getContentAsString(), Account.class);
    }

}
