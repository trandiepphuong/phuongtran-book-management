package com.phuongtd.book.controllers;

import com.phuongtd.book.entities.User;
import com.phuongtd.book.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
    @Autowired
    UserService userService;
    @GetMapping
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @PutMapping("/{id}/enable")
    public User enableUser(@PathVariable int id) throws NotFoundException {
        return userService.enable(id);
    }
    @PutMapping("/{id}/disable")
    public User disableUser(@PathVariable int id) throws NotFoundException {
        return userService.disable(id);
    }
}
