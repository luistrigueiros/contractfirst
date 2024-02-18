package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRecord;
import com.example.openapi.models.Course;

public class Mappers {
    public static Course from(CourseRecord record) {
        Course.CourseTypeEnum engineering = Course.CourseTypeEnum.fromValue(record.getCourseType());
        return new Course(record.getCourseName(), "" + record.getCourseDuration(), engineering);
    }

    public static CourseRecord from(Course course) {
        CourseRecord record = new CourseRecord();
        record.setCourseId(course.getCourseId().intValue());
        record.setCourseType(course.getCourseType().getValue());
        record.setCourseDuration(Integer.valueOf(course.getCourseDuration()));
        record.setCourseName(course.getCourseName());
        return record;
    }
}
