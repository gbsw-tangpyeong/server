package com.tpc.groot.user.service;

import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.google.GoogleAccountProfileDto;
import com.tpc.groot.user.entity.CustomUser;
import com.tpc.groot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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