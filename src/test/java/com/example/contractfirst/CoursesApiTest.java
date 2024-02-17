package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRepository;
import com.example.openapi.models.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoursesApiTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenAddCourseTheShouldHave201() throws Exception {
        Course courseToAdd = new Course("Chemistry", "4", Course.CourseTypeEnum.ENGINEERING);
        courseToAdd.setCourseId(new BigDecimal(5));
        webTestClient.post()
                .uri("/api/v1/courses")
                .bodyValue(courseToAdd)
                .exchange()
                .expectStatus().isCreated();
    }

}
