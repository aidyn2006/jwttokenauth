package org.example.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.auth.util.Role;


import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Username should not be empty!")
    @Size(min = 2, max = 10, message = "Username should be between 2 and 10 characters")
    private String username;

    @Email(message = "Email should be valid!")
    @NotEmpty(message = "Email should not be empty!")
    private String email;

    private String password;

    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

}
