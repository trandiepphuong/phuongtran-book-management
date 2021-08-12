package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.Login;
import com.phuongtd.book.entities.User;
import com.phuongtd.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Login login) throws Exception {
        return userService.login(login);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return  userService.register(user);
    }
}
