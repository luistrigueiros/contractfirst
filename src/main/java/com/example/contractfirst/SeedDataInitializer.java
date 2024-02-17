package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRepository;
import com.example.contractfirst.domain.StudentRepository;
import com.example.openapi.models.Course;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class SeedDataInitializer implements CommandLineRunner {
    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final AtomicInteger courseCounter = new AtomicInteger(1);

    private static List<Course> courseList = new ImmutableList
            .Builder<Course>()
            .add(new Course("Computer Science", "4", Course.CourseTypeEnum.ENGINEERING))
            .add(new Course("Mechanical Engineering", "4", Course.CourseTypeEnum.ENGINEERING))
            .add(new Course("Microbiology", "4", Course.CourseTypeEnum.MEDICAL))
            .build();

    public SeedDataInitializer(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    private Course setIdIn(Course course) {
        BigDecimal courseId = new BigDecimal(courseCounter.incrementAndGet());
        course.courseId(courseId);
        return course;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating seed data ....");
        long count = courseList.stream()
                .map(this::setIdIn)
                .map(Mappers::from)
                .peek(courseRepository::save)
                .toList()
                .size();
        log.info("Loaded {} courses", count);
    }
}
