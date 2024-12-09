package com.tpc.groot.status;

import com.tpc.groot.user.entity.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "status")
    private CustomUser user;

    private int totalDistance;
}