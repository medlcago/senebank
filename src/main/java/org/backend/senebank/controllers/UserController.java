package org.backend.senebank.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.senebank.dto.models.UserModel;
import org.backend.senebank.dto.request.JwtRequest;
import org.backend.senebank.dto.response.JwtResponse;
import org.backend.senebank.entities.User;
import org.backend.senebank.security.user.UserDetailsImpl;
import org.backend.senebank.services.Impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${api.v1.prefix}/users", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(value = "/me")
    private ResponseEntity<?> getMe(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(UserModel.fromEntity(user));
    }

    @PostMapping(value = "/login")
    private ResponseEntity<?> loginUser(@RequestBody JwtRequest request) {
        log.info("Login request: {}", request);
        String token = userService.login(request.email(), request.password());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@RequestBody JwtRequest request) {
        log.info("Register request: {}", request);
        String token = userService.create(request.email(), request.password());
        return ResponseEntity.status(201).body(new JwtResponse(token));
    }

    @DeleteMapping("/")
    private ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("Delete request: {}", userDetails.getEmail());
        User user = userDetails.getUser();
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
