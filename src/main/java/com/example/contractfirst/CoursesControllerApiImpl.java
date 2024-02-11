package com.example.contractfirst;

import com.example.openapi.CoursesApi;
import com.example.openapi.models.Course;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "${app.apiRoot}")
public class CoursesControllerApiImpl implements CoursesApi {
    public Map<Integer, Course> courseMap = new ImmutableMap.Builder<Integer, Course>()
            .put(1, new Course("Computer Science", "4", Course.CourseTypeEnum.ENGINEERING))
            .put(2, new Course("Mechanical Engineering", "4", Course.CourseTypeEnum.ENGINEERING))
            .put(3, new Course("Microbiology", "4", Course.CourseTypeEnum.MEDICAL))
            .build();

    @Override
    public ResponseEntity<List<Course>> getCourses(String sortBy) {
        List<Course> list = courseMap.values().stream().toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Course> getCourse(Integer courseId) {
        Course course = courseMap.getOrDefault(courseId, null);
        return ResponseEntity.ofNullable(course);
    }
}
