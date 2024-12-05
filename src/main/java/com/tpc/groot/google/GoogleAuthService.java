package com.tpc.groot.google;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final GoogleClient googleClient;

    public GoogleAccountProfileDto getProfileFromGoogle(String code) {
        String accessToken = googleClient.requestGoogleAccessToken(code);
        return googleClient.getGoogleAccountProfile(accessToken);
    }
}
