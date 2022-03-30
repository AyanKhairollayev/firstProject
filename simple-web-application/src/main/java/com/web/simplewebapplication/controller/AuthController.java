package com.web.simplewebapplication.controller;

import com.web.simplewebapplication.dto.request.LoginDtoRequest;
import com.web.simplewebapplication.dto.request.RegistrationDtoRequest;
import com.web.simplewebapplication.dto.request.UserUpdateDtoRequest;
import com.web.simplewebapplication.dto.response.UserDtoResponse;
import com.web.simplewebapplication.models.User;
import com.web.simplewebapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auths")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDtoRequest dtoRequest) {
        return ResponseEntity.ok(authService.login(dtoRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDtoRequest dtoRequest) {
        return ResponseEntity.ok(authService.registration(dtoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authService.deleteUser(id));
    }

    @GetMapping()
    public ResponseEntity<List<User>> showAll() {
        return ResponseEntity.ok(authService.showAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<User>> showByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(authService.showByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> showById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authService.showById(id));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDtoRequest dtoRequest) {
        return ResponseEntity.ok(authService.updateUser(id, dtoRequest));
    }
}
