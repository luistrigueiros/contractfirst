package com.example.contractfirst.util;

import com.example.openapi.models.Course;

public class CourseTypeEnumMapper {
    public Course.CourseTypeEnum from(String value) {
        return Course.CourseTypeEnum.fromValue(value);
    }

    public String from (Course.CourseTypeEnum value) {
        return value.getValue();
    }
}
