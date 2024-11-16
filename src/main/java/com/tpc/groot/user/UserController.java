package com.tpc.groot.user;

import com.tpc.groot.user.dto.CreateUserDto;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CustomUser> createUser(@RequestBody @Valid CreateUserDto dto) {
        CustomUser user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user); // 상태 코드 추가
    }
}