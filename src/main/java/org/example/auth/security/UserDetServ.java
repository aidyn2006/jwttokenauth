package org.example.auth.security;

import org.example.auth.model.User;
import org.example.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetServ implements UserDetailsService {
    private UserRepository userRepository;


    @Autowired
    public UserDetServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);

        if(!user.isPresent()){
            throw new UsernameNotFoundException("Username not found!");
        }
        User user1=user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(user1.getUsername())
                .password(user1.getPassword())
                .roles(String.valueOf(user1.getRole()))
                .build();
    }
}
