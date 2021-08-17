package com.phuongtd.book.configurations;

import com.phuongtd.book.entities.User;
import com.phuongtd.book.repositories.RoleRepository;
import com.phuongtd.book.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${jwt-key}")
    private String signingKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void addUserIfMissing(String email, String password, String role) {
        if (userRepository.findByEmail(email) == null) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRole(roleRepository.findByName(role));
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addUserIfMissing("user@gmail.com", "456", "ROLE_USER");
        addUserIfMissing("admin@gmail.com", "123", "ROLE_ADMIN");
        addUserIfMissing("superadmin@gmail.com", "123", "ROLE_SUPERADMIN");
        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("Book")
                    .signWith(SignatureAlgorithm.HS256, "BookApi").compact();
        }
    }
}
