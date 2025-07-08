package com.devteamup.api;

import com.devteamup.*;
import com.devteamup.model.User;
import com.devteamup.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user);
        
        
    }
    @GetMapping("/test")
    public String test() {
        return "API is accessible!";
    }
}
