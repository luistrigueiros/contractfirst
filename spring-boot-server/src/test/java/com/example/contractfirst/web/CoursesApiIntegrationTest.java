package com.example.contractfirst.web;

import com.example.openapi.models.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoursesApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void whenAddCourseThenShouldHave201() throws Exception {
        Course courseToAdd = new Course("Chemistry", "4", Course.CourseTypeEnum.ENGINEERING);
        courseToAdd.setCourseId(new BigDecimal(5));
        String content = objectMapper.writeValueAsString(courseToAdd);
        mockMvc.perform(post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void whenGetCoursesThenShouldHave200() throws Exception {
        mockMvc.perform(get("/api/v1/courses")).andExpect(status().isOk());
    }
}
