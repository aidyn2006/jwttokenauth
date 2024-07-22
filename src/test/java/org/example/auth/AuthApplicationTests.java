package org.example.auth;

import org.example.auth.model.User;
import org.example.auth.repo.UserRepository;
import org.example.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthApplicationTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUsername() {
        User user = new User();
        user.setUsername("A");

        when(userRepository.findByUsername("A")).thenReturn(Optional.of(user));

        Optional<User> optionalUser = userService.getUsername("A");

        assertThat(optionalUser).isPresent();
        assertThat(optionalUser.get().getUsername()).isEqualTo("A");
    }
}
