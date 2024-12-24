package com.tpc.groot.course.service;

import com.nimbusds.jose.util.Pair;
import com.tpc.groot.course.dto.CourseDto;
import com.tpc.groot.course.entity.Course;
import com.tpc.groot.course.entity.Polyline;
import com.tpc.groot.course.repository.CourseRepository;
import com.tpc.groot.course.repository.PolylineRepository;
import com.tpc.groot.status.Status;
import com.tpc.groot.status.StatusRepository;
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

    private Polyline latlngToPolyline(int seq, Pair<Float, Float> latlng, Course course) {
        Polyline p = new Polyline(latlng.getLeft(), latlng.getRight(), seq, course);
        return polylineRepository.save(p);
    }

    public Course createCourse(CourseDto dto) {
        Course course = new Course(dto.getTitle(), dto.getTotalDistance());

        List<Pair<Float, Float>> latlngs = dto.getLatLngs();
        int seq = 0;
        List<Polyline> polylines = new ArrayList<>();
        for (Pair<Float, Float> latlng : latlngs) {
            polylines.add(latlngToPolyline(++seq, latlng, course));
        }
        course.setPolylines(polylines);

        return courseRepository.save(course);
    }
}
