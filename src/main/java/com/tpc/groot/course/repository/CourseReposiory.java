package com.tpc.groot.course.repository;

import com.tpc.groot.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseReposiory extends JpaRepository<Course, Long> {
}
