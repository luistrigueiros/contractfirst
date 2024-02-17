package com.example.contractfirst.web;

import com.example.contractfirst.domain.CourseRepository;
import com.example.contractfirst.persistance.InMemoryCourseRepository;
import com.example.openapi.models.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoursesControllerApiImpl.class)
@ContextConfiguration(classes = {CoursesControllerApiImpl.class, InMemoryCourseRepository.class})
class CoursesApiTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenAddCourseTheShouldHave201() throws Exception {
        Course course = new Course("Chemistry", "4", Course.CourseTypeEnum.ENGINEERING);
        course.setCourseId( new BigDecimal(5));
        String json = objectMapper.writeValueAsString(course);
        MockHttpServletRequestBuilder builder = post("/api/v1/courses")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(builder)
                .andExpect(status().is(201));
    }

}
