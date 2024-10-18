package com.example.crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserRepository userRepository;
@GetMapping("")
public List<User> getAllUsers() {
    return userRepository.findAll();
}

@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
    User user = userRepository.findById(id).orElse(null);
    if (user == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
}
@PostMapping("")
public ResponseEntity<User> createUser(@RequestBody User user){
    User savedUser = userRepository.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
}
@PutMapping("/{id}")
public ResponseEntity<User> updateUser(@PathVariable("id") Long id,@RequestBody User user) {
    User currentUser = userRepository.findById(id).orElse(null);
    if (currentUser ==null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    currentUser.setName(user.getName());
    currentUser.setEmail(user.getEmail());
    currentUser.setPassword(user.getPassword());
    User updatedUser = userRepository.save(currentUser);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
    User user = userRepository.findById(id).orElse(null);
    if (user == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    userRepository.delete(user);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
