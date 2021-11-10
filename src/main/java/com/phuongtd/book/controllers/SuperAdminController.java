package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.User;
import com.phuongtd.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public List<User> getAllAccount(){
        return userService.findAll();
    }
    @PutMapping("/users/{id}/set-admin")
    public User setAdmin(@PathVariable int id) throws Exception {
        return userService.setAdmin(id);
    }
    @PutMapping("/users/{id}/remove-admin")
    public User removeAdmin(@PathVariable int id) throws Exception {
        return userService.removeAdmin(id);
    }
}
