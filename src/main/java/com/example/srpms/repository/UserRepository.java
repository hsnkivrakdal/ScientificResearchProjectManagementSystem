package com.example.srpms.repository;

import com.example.srpms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserEmailAndUserPassword(String email, String password);
}
