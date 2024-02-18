package com.example.contractfirst.web;

import com.example.contractfirst.domain.CourseRepository;
import com.example.openapi.models.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CoursesControllerApiImpl.class})
class CoursesApiValidationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CourseRepository courseRepository;

    @Test
    void postingInvalidCourseShouldFailWithClientError() throws Exception {
        Course courseToAdd = new Course(null, "4", Course.CourseTypeEnum.ENGINEERING);
        mockMvc.perform(post("/api/v1/courses")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(courseToAdd)))
                .andExpect(status().is4xxClientError());
    }

}
