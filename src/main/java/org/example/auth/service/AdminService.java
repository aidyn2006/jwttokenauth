package org.example.auth.service;


import org.example.auth.model.User;
import org.example.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {
    private final UserRepository repository;


    @Autowired
    public AdminService(UserRepository userRepository){
        this.repository=userRepository;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteByUserName(String username){
        System.out.println("Started");
        Optional<User> user=repository.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Not Found");
        }
        else {
            repository.deleteByUsername(username);
        }
        System.out.println("Ended");

    }
}
