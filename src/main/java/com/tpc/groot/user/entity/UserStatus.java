package com.tpc.groot.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserStatus {

    @OneToOne
    private User user;

}
