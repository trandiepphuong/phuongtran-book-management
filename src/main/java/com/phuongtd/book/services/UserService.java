package com.phuongtd.book.services;

import com.phuongtd.book.configurations.TokenProvider;
import com.phuongtd.book.entities.Login;
import com.phuongtd.book.entities.User;
import com.phuongtd.book.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private RoleService roleService;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<User> login(Login login) {
        User user = userRepository.findByEmail(login.getUsername());
        if (user.getEnabled() == true) {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getUsername(),
                            login.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            user.setToken(token);
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public String register(User user) {
        if (this.findByEmail(user.getEmail()) == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRole(roleService.findByName("ROLE_USER"));
            user.setEnabled(true);
            this.save(user);
            return "Register successful!";
        }
        return ("Email has already been used! Please register again!");
    }

    public User setAdmin(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        boolean alreadyAdmin = false;
        if (user.isPresent()) {
            if (user.get().getRole().getName().equals("ROLE_ADMIN")) alreadyAdmin = true;
            if (alreadyAdmin == false) {
                user.get().setRole(roleService.findByName("ROLE_ADMIN"));
                userRepository.save(user.get());
            }
            return user.get();
        }
        throw new Exception();
    }

    public User removeAdmin(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        boolean alreadyAdmin = false;
        if (user.isPresent()) {
            if (user.get().getRole().getName().equals("ROLE_ADMIN")) alreadyAdmin = true;
            if (alreadyAdmin == true) {
                user.get().setRole(roleService.findByName("ROLE_USER"));
                userRepository.save(user.get());
            }
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public User enable(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setEnabled(true);
            userRepository.save(user.get());
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public User disable(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setEnabled(false);
            userRepository.save(user.get());
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public List<User> findAllUser() {
        List<User> allUser = userRepository.findAllByOrderById();
        List<User> result = new ArrayList<>();
        for (User user : allUser) {
            if (user.getRole().getId() == 2) result.add(user);
        }
        return result;
    }
}
