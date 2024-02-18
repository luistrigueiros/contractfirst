package com.example.contractfirst.util;

import com.example.contractfirst.domain.CourseRecord;
import com.example.openapi.models.Course;
import org.mapstruct.Mapper;

@Mapper(uses = {CourseTypeEnumMapper.class})
public interface Mappers {
    Mappers INSTANCE = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

     CourseRecord courseToCourseRecord(Course course);

     Course courseRecordToCourse(CourseRecord record);

}
