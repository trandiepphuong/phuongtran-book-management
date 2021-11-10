package com.phuongtd.book.services;

import com.phuongtd.book.entities.Role;
import com.phuongtd.book.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
