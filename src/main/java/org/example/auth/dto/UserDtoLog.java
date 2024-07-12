package org.example.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDtoLog {

    @NotEmpty(message = "Username should not be empty!")
    @Size(min = 2, max = 10, message = "Username should be between 2 and 10 characters")
    private String username;


    private String password;
}
