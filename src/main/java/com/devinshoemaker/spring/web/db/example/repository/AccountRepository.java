package com.devinshoemaker.spring.web.db.example.repository;

import com.devinshoemaker.spring.web.db.example.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Database operations for the user table.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
