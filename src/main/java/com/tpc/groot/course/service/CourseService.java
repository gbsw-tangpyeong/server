package com.tpc.groot.course.service;

import com.tpc.groot.course.dto.CourseDto;
import com.tpc.groot.course.entity.Course;
import com.tpc.groot.course.entity.LatLng;
import com.tpc.groot.course.entity.Polyline;
import com.tpc.groot.course.repository.CourseRepository;
import com.tpc.groot.course.repository.PolylineRepository;
import com.tpc.groot.user.entity.Status;
import com.tpc.groot.user.repository.StatusRepository;
import com.tpc.groot.user.repository.UserRepository;
import com.tpc.groot.user.entity.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final CourseRepository courseRepository;
    private final PolylineRepository polylineRepository;

    public Status UpdateTotalDistance(long courseId, int distance){
        Optional<Course> course = courseRepository.findById(courseId);
        CustomUser user = userRepository.findByStatusCoursesId(courseId);
        Status status = user.getStatus();
        status.setTotalDistance(status.getTotalDistance() + distance);

        return statusRepository.save(status);
    }

    public Course UpdateRanDistance(long courseId, int distance) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()){
            course.get().setRanDistance(course.get().getRanDistance() + distance);
            return courseRepository.save(course.get());
        }
        else return null;
    }

    private Polyline latlngToPolyline(int seq, LatLng latlng, Course course) {
        Polyline p = new Polyline(latlng.getLat(), latlng.getLng(), seq, course);
        return polylineRepository.save(p);
    }

    public Course createCourse(CustomUser user, CourseDto dto) {
        Status status = statusRepository.findByCustomUser(user);
        Course course = new Course(dto.getTitle(), dto.getTotalDistance(), status);

        List<LatLng> latlngs = dto.getLatLngs();
        int seq = 0;
        List<Polyline> polylines = new ArrayList<>();
        for (LatLng latlng : latlngs) {
            polylines.add(latlngToPolyline(++seq, latlng, course));
        }
        course.setPolylines(polylines);

        return courseRepository.save(course);
    }

    public Course getCourse(long courseId, CustomUser user) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            if (course.get().getStatus().getUser() == user) {
                return course.get();
            }
        }
        return null;
    }

    public void deleteCourse(long courseId, CustomUser user) {
        Course course = getCourse(courseId, user);
        courseRepository.delete(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
