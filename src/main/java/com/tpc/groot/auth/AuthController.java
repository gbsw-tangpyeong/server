package com.tpc.groot.auth;

import com.tpc.groot.user.dto.LoginUserDto;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDto dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }
}
