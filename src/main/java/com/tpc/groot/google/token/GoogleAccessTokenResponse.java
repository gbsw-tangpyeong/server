package com.tpc.groot.google.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleAccessTokenResponse {
    private String accessToken;
    private String tokenType; // 토큰 유형
    private Long expiresIn; // 만료 시간 (초)
    private String refreshToken;
    private String scope; // 토큰의 권한 범위
}