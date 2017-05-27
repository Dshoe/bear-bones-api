package com.devinshoemaker.bear.bones.api.repository;

import com.devinshoemaker.bear.bones.api.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Database operations for the user table.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
