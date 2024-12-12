package com.tpc.groot.google.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleAccessTokenRequest {
    private String code; // 구글의 인증코드
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType; // 인증 유형
}