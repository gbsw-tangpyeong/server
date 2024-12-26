package com.tpc.groot.user.repository;

import com.tpc.groot.user.entity.CustomUser;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByUsername(String username);
    CustomUser findByEmail(String email);

    CustomUser findByStatusCoursesId(long courseId);

    List<CustomUser> findByOrderByStatusTotalDistanceDesc();

    List<CustomUser> findByOrderByStatusTotalDistanceDesc(Limit limit);
}