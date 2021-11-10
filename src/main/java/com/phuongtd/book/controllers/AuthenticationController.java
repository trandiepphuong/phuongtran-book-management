package com.phuongtd.book.controllers;

import com.phuongtd.book.models.GooglePojo;
import com.phuongtd.book.models.Login;
import com.phuongtd.book.entities.User;
import com.phuongtd.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login-google")
    public ResponseEntity<User> loginGoogle(@RequestBody GooglePojo googlePojo) throws Exception {
        return userService.loginGoogle(googlePojo);
    }
}
