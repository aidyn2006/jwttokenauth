package org.example.auth.service;


import org.example.auth.dto.UserDtoReg;
import org.example.auth.model.User;
import org.example.auth.repo.UserRepository;
import org.example.auth.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(UserDtoReg userDtoReg) {
        User user = User.builder()
                .username(userDtoReg.getUsername())
                .email(userDtoReg.getEmail())
                .password(passwordEncoder.encode(userDtoReg.getPassword()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        System.out.println("login");
    }

    public Optional<User> getUsername(String username){
        return userRepository.findByUsername(username);
    }




}
