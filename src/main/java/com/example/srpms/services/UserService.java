package com.example.srpms.services;

import com.example.srpms.models.User;
import com.example.srpms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BusinessServiceImplementation<User,Integer> {
    protected UserService(UserRepository repository) {
        super(repository);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = repository.findAll().stream().filter(user1 -> user1.getUserEmail().equals(email) && user1.getUserPassword().equals(password)).findFirst().orElse(null);
        return user;
    }
}
