package com.example.contractfirst.web;

import com.example.contractfirst.domain.CourseRecord;
import com.example.contractfirst.domain.CourseRepository;
import com.example.contractfirst.util.Mappers;
import com.example.openapi.CoursesApi;
import com.example.openapi.models.Course;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "${app.apiRoot}")
public class CoursesControllerApiImpl implements CoursesApi {
    private final CourseRepository courseRepository;
    private final NativeWebRequest request;

    public CoursesControllerApiImpl(CourseRepository courseRepository, NativeWebRequest request) {
        this.courseRepository = courseRepository;
        this.request = request;
    }

    private void debugPrintUserPrincipal() {
        Authentication authentication = (Authentication) request.getUserPrincipal();
        if (authentication != null) {
            log.debug("Is authenticate ={}", authentication.isAuthenticated());
            List<String> list = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            log.debug("Granted authorities {}" , String.join(",", list));
        }
    }

    @Override
    public ResponseEntity<List<Course>> getCourses(String sortBy) {
        debugPrintUserPrincipal();
        Iterable<CourseRecord> all = courseRepository.findAll();
        List<Course> list = ImmutableList.copyOf(all)
                .stream()
                .map(Mappers.INSTANCE::courseRecordToCourse)
                .toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Course> getCourse(Integer courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId))
                .map(Mappers.INSTANCE::courseRecordToCourse)
                .orElse(null);
        return ResponseEntity.ofNullable(course);
    }

    @Override
    public ResponseEntity<Course> addCourse(Course course) {
        CourseRecord courseRecord = Mappers.INSTANCE.courseToCourseRecord(course);
        courseRepository.save(courseRecord);
        return new ResponseEntity<>(course, HttpStatusCode.valueOf(201));
    }
}
