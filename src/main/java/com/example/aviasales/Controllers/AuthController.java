package com.example.aviasales.Controllers;

import com.example.aviasales.DTO.AuthUserDTO;
import com.example.aviasales.Domain.User;
import com.example.aviasales.ExceptionHandlers.UserValidationException;
import com.example.aviasales.ExceptionHandlers.Validators.UserValidator;
import com.example.aviasales.Security.JwtTokenProvider;
import com.example.aviasales.Services.AuthService;
import com.example.aviasales.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserValidator accountValidator;
    private final UserService accountService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController
            (AuthService authService,
             UserValidator accountValidator,
             UserService accountService,
             JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.accountValidator = accountValidator;
        this.accountService = accountService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Register(@Valid @RequestBody AuthUserDTO accountDetails, BindingResult errors)
    {
        accountValidator.validate(accountDetails, errors);

        if(errors.hasErrors()) {
            throw new UserValidationException(errors);
        }

        User account = accountDetails.ToUser();
        authService.signup(account);
//        log.info("Get request : /api/v1/auth/registerStudent");
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signin(@RequestBody AuthUserDTO accountDetails) {
        String login = accountDetails.getLogin();
        User account = accountService.findByLogin(login);
        String token = jwtTokenProvider.createToken(login);
        Map<Object, Object> response = new HashMap<>();
        response.put("JWT", token);
        response.put("accountId", account.getId());
        return ResponseEntity.ok(response);
    }
}
