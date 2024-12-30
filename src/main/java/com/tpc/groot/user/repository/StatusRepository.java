package com.tpc.groot.user.repository;

import com.tpc.groot.user.entity.Status;
import com.tpc.groot.user.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByCustomUser(CustomUser customUser);
}
