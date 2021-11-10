package com.phuongtd.book.repositories;

import com.phuongtd.book.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByEmailAndLoginWithGoogle(String email, Boolean loginWithGoogle);
    List<User> findAllByOrderById();
}
