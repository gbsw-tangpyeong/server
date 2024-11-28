package com.tpc.groot.course.repository;

import com.tpc.groot.course.entity.Course;
import com.tpc.groot.user.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    CustomUser findByUser(CustomUser user);
}
