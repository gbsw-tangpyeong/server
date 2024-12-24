package com.tpc.groot.user.service;

import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.google.GoogleAccountProfileDto;
import com.tpc.groot.user.entity.CustomUser;
import com.tpc.groot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getRanking(int number) {
        // 상위 n명
        List<CustomUser> ranking = userRepository.findByStatusOrderByStatusTotalDistanceDesc(Limit.of(number));

        Map<String, Object> result = new HashMap<>();
        result.put("ranking", ranking);

        return result;
    }

    public Map<String, Object> getRanking(CustomUser user, int number) {
        // 상위n명
        List<CustomUser> ranking = userRepository.findByStatusOrderByStatusTotalDistanceDesc(Limit.of(number));

        // 전체
        List<CustomUser> allUsers = userRepository.findByStatusOrderByStatusTotalDistanceDesc();

        int myRank = 0;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(user.getUsername())) {
                myRank = i + 1;
                break;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("ranking", ranking); // 상위 n
        result.put("userRank", myRank); // user의 순위

        return result;
    }

}