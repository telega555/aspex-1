package com.example.aspex1.repository;


import com.example.aspex1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
    User getUserById(Long userI);

    User findByPhone(String phone);

}