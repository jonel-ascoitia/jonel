package com.senati.senati_web_ascoitia.service;

import com.senati.senati_web_ascoitia.model.User;
import com.senati.senati_web_ascoitia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> saveUser(User user) {
        //User savedUser = userRepository.save(user);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<User> updateUser(Integer id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalUser.get();
        existingUser.setName(userDetails.getName());
        existingUser.setLastname(userDetails.getLastname());

        User updatedUser = userRepository.save(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
