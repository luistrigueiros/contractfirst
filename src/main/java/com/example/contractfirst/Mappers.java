package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRecord;
import com.example.openapi.models.Course;
public class Mappers {
    public static Course from(CourseRecord record) {
        Course.CourseTypeEnum engineering = Course.CourseTypeEnum.valueOf(record.courseType());
        return new Course(record.courseName(), "" + record.courseDuration(), engineering);
    }

    public static CourseRecord from(Course course) {
        Integer courseId = course.getCourseId().intValue();
        Integer courseDuration = Integer.valueOf(course.getCourseDuration());
        String courseType = course.getCourseType().getValue();
        return new CourseRecord(courseId, course.getCourseName(), courseDuration, courseType);
    }
}
