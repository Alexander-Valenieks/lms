package com.backend.lms.controller;

import com.backend.lms.dto.LoginDTO;
import com.backend.lms.dto.UserDTO;
import com.backend.lms.entity.User;
import com.backend.lms.exception.UserNotFoundException;
import com.backend.lms.payload.LoginMessage;
import com.backend.lms.repository.UserRepository;
import com.backend.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String id = userService.addUser(userDTO);
        return id;
    }

//    @PostMapping("/user")
//    User newUser(@RequestBody User newUser) {
//        return userRepository.save(newUser);
//    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

    @GetMapping(path = "/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable int id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable int id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully";
    }
}
