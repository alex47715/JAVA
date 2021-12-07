package com.example.aviasales.ExceptionHandlers.Validators;

import com.example.aviasales.DTO.AuthUserDTO;
import com.example.aviasales.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthUserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AuthUserDTO account = (AuthUserDTO) obj;

        if(accountService.findByLogin(account.getLogin()) != null) {
            errors.rejectValue("login", "", "This login is already in use");
        }
    }
}
