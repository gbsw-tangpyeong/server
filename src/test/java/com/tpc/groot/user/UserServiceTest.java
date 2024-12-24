package com.tpc.groot.user;

import com.tpc.groot.jwt.JwtProperties;
import com.tpc.groot.jwt.TokenProvider;
import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.user.entity.CustomUser;
import com.tpc.groot.user.repository.UserRepository;
import com.tpc.groot.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private JwtProperties jwtProperties;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ShouldSaveToRepository() {
        // given
        CreateUserDto dto = new CreateUserDto();
        dto.setUsername("testUser");
        dto.setPassword("testPassword");
        dto.setEmail("user@example.com");
        dto.setPhone("123-456-7890");
        dto.setProfileImg("https://example.com/profile.jpg");
        dto.setAddress("123 Sample Street, City, Country");

        CustomUser expectedUser = new CustomUser();
        expectedUser.setUsername(dto.getUsername());
        expectedUser.setPassword("encodedPassword");
        expectedUser.setEmail(dto.getEmail());
        expectedUser.setPhone(dto.getPhone());
        expectedUser.setProfileImg(dto.getProfileImg());
        expectedUser.setAddress(dto.getAddress());
        expectedUser.setCreatedAt(LocalDateTime.now());

        // password encoding and user repository saving
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(CustomUser.class))).thenReturn(expectedUser);

        // when
        CustomUser savedUser = userService.create(dto);

        // then
        assertThat(savedUser.getUsername()).isEqualTo(dto.getUsername());
        assertThat(savedUser.getPassword()).isEqualTo("encodedPassword");
        assertThat(savedUser.getEmail()).isEqualTo(dto.getEmail());
        assertThat(savedUser.getPhone()).isEqualTo(dto.getPhone());
        assertThat(savedUser.getProfileImg()).isEqualTo(dto.getProfileImg());
        assertThat(savedUser.getAddress()).isEqualTo(dto.getAddress());

        // verify save method call
        verify(userRepository).save(any(CustomUser.class));
    }
}
