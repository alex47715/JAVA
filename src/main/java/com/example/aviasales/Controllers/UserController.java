package com.example.aviasales.Controllers;

import com.example.aviasales.Domain.User;
import com.example.aviasales.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "info")
    public ResponseEntity<User> getAccountInfo(@RequestParam Map<String, String> mapParam) {
        Integer accountId = Integer.parseInt(mapParam.get("accountId"));
        User user = userService.findById(accountId);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping(value = "all")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> user = userService.findAll();
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Integer accountId = Integer.parseInt(mapParam.get("accountId"));
        User account = userService.findById(accountId);
        Boolean isAdmin = "ROLE_ADMIN".equals(account.getRole().getName());
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }
}
