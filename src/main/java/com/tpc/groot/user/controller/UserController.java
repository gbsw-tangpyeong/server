package com.tpc.groot.user.controller;

import com.tpc.groot.status.Status;
import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.user.entity.CustomUser;
import com.tpc.groot.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CustomUser> createUser(@RequestBody @Valid CreateUserDto dto) {
        CustomUser user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<CustomUser> getUser(@PathVariable String username) {
        CustomUser user = userService.getProfile(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/rank")
    public ResponseEntity<Map<String, Object>> getUserRanking() {
        Map<String, Object> rank = userService.getRanking(15);
        return ResponseEntity.ok(rank);
    }

    @GetMapping("/rank/{username}")
    public ResponseEntity<Map<String, Object>> getUserRanking(@PathVariable String username) {
        CustomUser user = userService.getProfile(username);
        Map<String, Object> rank = userService.getRanking(user, 15);
        return ResponseEntity.ok(rank);
    }
}