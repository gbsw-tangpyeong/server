package com.tpc.groot.auth;

import com.tpc.groot.google.GoogleAccountProfileDto;
import com.tpc.groot.google.GoogleClient;
import com.tpc.groot.jwt.TokenProvider;
import com.tpc.groot.user.dto.LoginUserDto;
import com.tpc.groot.user.entity.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;
    private final GoogleClient googleClient;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDto dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/login/oauth2/google")
    public ResponseEntity<String> googleLogin(@RequestParam String code) {
        String accessToken = googleClient.requestGoogleAccessToken(code);
        GoogleAccountProfileDto profileDto = googleClient.getGoogleAccountProfile(accessToken);

        CustomUser user = authService.createUserGoogle(profileDto);

        String token = tokenProvider.generateToken(user, Duration.ofHours(1));
        return ResponseEntity.ok(token);
    }
}
