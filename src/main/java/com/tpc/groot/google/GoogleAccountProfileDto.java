package com.tpc.groot.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleAccountProfileDto {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String email;
    private String picture;
    private boolean email_verified;
}
