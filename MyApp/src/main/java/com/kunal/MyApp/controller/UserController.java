package com.kunal.MyApp.controller;

import com.kunal.MyApp.request.SignUpRequestPojo;
import com.kunal.MyApp.service.impl.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    public UserController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserToFirestore(@RequestBody SignUpRequestPojo sign) {
        try {
            firebaseService.storeUserData(sign.getFirstName(), sign.getMobileNumber());
            return ResponseEntity.ok("User data stored successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to store user data: " + e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserData(@PathVariable String username) {
        try {
            Map<String, Object> userData = firebaseService.getUserData(username);
            return ResponseEntity.ok(userData);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody SignUpRequestPojo sign) {
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("username",sign.getFirstName());
        updatedData.put("mobileNumber",sign.getMobileNumber());
        try {
            firebaseService.updateUserData(username, updatedData);
            return ResponseEntity.ok("User data updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to update user data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            firebaseService.deleteUserData(username);
            return ResponseEntity.ok("User data deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Failed to delete user data: " + e.getMessage());
        }
    }

}
