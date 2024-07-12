package org.example.auth.controller;

import org.example.auth.dto.UserDtoLog;
import org.example.auth.dto.UserDtoReg;
import org.example.auth.model.User;
import org.example.auth.security.JWT.JwtUtil;
import org.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> logIn(@RequestBody UserDtoLog log) {
        // TODO: Implement authentication logic
        String jwtToken = jwtUtil.generateToken(log.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDtoReg reg) {
        // TODO: Implement registration logic
        service.save(reg);
        String token = jwtUtil.generateToken(reg.getUsername());
        return ResponseEntity.ok(token);
    }
    @GetMapping("/main/{username}")
    public Optional<User> mainPage(@PathVariable String username){
        return ResponseEntity.ok(service.getUsername(username)).getBody();
    }

    public static class JwtResponse {
        private final String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
