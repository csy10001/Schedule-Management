package hello.schedule.controller;

import hello.schedule.dto.UserRequest;
import hello.schedule.dto.UserResponse;
import hello.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> save(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.save(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.save(request));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
