package com.devinshoemaker.spring.web.db.example.validator;

import com.devinshoemaker.spring.web.db.example.domain.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by devin on 5/15/17.
 */
public class AccountValidator implements Validator {

    private boolean isUpdating = false;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account account = (Account) o;
        if (account.getId() != null) {
            errors.rejectValue("id", "not.null");
        }
    }

    public void validate(Object o, Errors errors, boolean isUpdating) {
        this.isUpdating = true;
        validate(o, errors);
    }

}
