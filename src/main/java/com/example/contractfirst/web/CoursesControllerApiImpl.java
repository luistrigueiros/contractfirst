package com.example.contractfirst.web;

import com.example.contractfirst.Mappers;
import com.example.contractfirst.domain.CourseRecord;
import com.example.contractfirst.domain.CourseRepository;
import com.example.openapi.CoursesApi;
import com.example.openapi.models.AddCourse201Response;
import com.example.openapi.models.Course;
import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "${app.apiRoot}")
public class CoursesControllerApiImpl implements CoursesApi {
    private final CourseRepository courseRepository;

    public CoursesControllerApiImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity<List<Course>> getCourses(String sortBy) {
        Iterable<CourseRecord> all = courseRepository.findAll();
        List<Course> list = ImmutableList.copyOf(all)
                .stream()
                .map(Mappers::from)
                .toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Course> getCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .map(Mappers::from)
                .orElse(null);
        return ResponseEntity.ofNullable(course);
    }

    @Override
    public ResponseEntity<AddCourse201Response> addCourse(Course course) {
        CourseRecord courseRecord = Mappers.from(course);
        CourseRecord save = courseRepository.save(courseRecord);
        AddCourse201Response.CourseTypeEnum courseTypeEnum = AddCourse201Response.CourseTypeEnum.fromValue(courseRecord.courseType());
        String courseDuration = "" + courseRecord.courseDuration();
        AddCourse201Response response = new AddCourse201Response(save.courseName(), courseDuration, courseTypeEnum);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
    }
}
