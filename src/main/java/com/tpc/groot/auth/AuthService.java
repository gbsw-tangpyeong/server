package com.tpc.groot.auth;

import com.tpc.groot.jwt.JwtProperties;
import com.tpc.groot.jwt.TokenProvider;
import com.tpc.groot.user.UserRepository;
import com.tpc.groot.user.dto.LoginUserDto;
import com.tpc.groot.user.entity.CustomUser;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginUserDto dto) {
        CustomUser user = userRepository.findByUsername(dto.getUsername());

        if (user == null) {
            throw new IllegalArgumentException("User Not Found");
        }
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password Incorrect");
        }

        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }
}
