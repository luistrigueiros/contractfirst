package com.example.contractfirst;

import com.example.openapi.CoursesApi;
import com.example.openapi.models.GetCourses200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesControllerApiImpl implements CoursesApi {
    @Override
    public ResponseEntity<GetCourses200Response> getCourses(String sortBy) {
        return CoursesApi.super.getCourses(sortBy);
    }
}
