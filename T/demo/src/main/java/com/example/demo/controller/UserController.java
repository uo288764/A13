package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost")
public class UserController {

    private final UserService userService;

    // Constructor to initialize userService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/scores")
    public ResponseEntity<String> postScores(@RequestBody UserDto userDto) {
        try {
            String result = userService.postScores(userDto);
            return ResponseEntity.ok(result); // Return HTTP 200 with the success message
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getallResult")
    public ResponseEntity<List<User>> getAllResults() {
        try {
            List<User> users = userService.getAllScores();
            return ResponseEntity.ok(users);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updatescore")
    public ResponseEntity<User> updateUser(
            @RequestParam String email,
            @RequestParam boolean win) {
        try {
            User user = userService.updateUserScore(email, win);
            return ResponseEntity.ok(user);
        } catch (Exception err) {
            System.err.println("Exception: " + err.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
