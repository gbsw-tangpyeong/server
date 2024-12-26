package com.tpc.groot.auth;

import com.tpc.groot.google.GoogleAccountProfileDto;
import com.tpc.groot.jwt.TokenProvider;
import com.tpc.groot.user.repository.UserRepository;
import com.tpc.groot.user.dto.LoginUserDto;
import com.tpc.groot.user.entity.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginUserDto dto) {
        CustomUser user = userRepository.findByUsername(dto.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }

    public CustomUser createOrGetUserFromGoogle(GoogleAccountProfileDto dto) {
        CustomUser user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new CustomUser(
                    dto.getName(),
                    passwordEncoder.encode("google_user_password"),
                    dto.getEmail(),
                    "",
                    "",
                    LocalDateTime.now()
                    );
            userRepository.save(user);
        }
        return user;
    }
}
