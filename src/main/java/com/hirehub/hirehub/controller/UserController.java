package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.dto.request.UserRequest;
import com.hirehub.hirehub.dto.response.UserResponse;
import com.hirehub.hirehub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequests) {
        UserResponse userResponse = userService.createUser(userRequests);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();

        return ResponseEntity.ok().body(userResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@Valid @RequestBody UserRequest userRequests, @PathVariable Long id) {
        UserResponse userResponse = userService.updateUserById(id, userRequests);

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted");
    }
}
