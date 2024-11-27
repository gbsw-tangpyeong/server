package com.tpc.groot.user;

import com.tpc.groot.jwt.JwtProperties;
import com.tpc.groot.jwt.TokenErrorResponse;
import com.tpc.groot.jwt.TokenProvider;
import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.user.dto.LoginUserDto;
import com.tpc.groot.user.entity.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUser create(CreateUserDto dto) {
        LocalDateTime now = LocalDateTime.now();

        CustomUser user = new CustomUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCreatedAt(now);

        return userRepository.save(user);
    }

    public CustomUser getProfile(String userName) {
        return userRepository.findByUsername(userName);
    }
}