package com.homecomfort.homecomfort.service;

import com.homecomfort.homecomfort.entity.User;
import com.homecomfort.homecomfort.exception.UserNotFoundException;
import com.homecomfort.homecomfort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        if (user == null || user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User or username cannot be null or empty");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (user == null || user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User or username cannot be null or empty");
        }
        User userForUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userForUpdate.setName(user.getName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        return userRepository.save(userForUpdate);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
