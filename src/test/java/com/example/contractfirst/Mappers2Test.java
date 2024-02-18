package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRecord;
import com.example.contractfirst.util.Mappers;
import com.example.openapi.models.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


@Slf4j
class Mappers2Test {
    @Test
    void shouldMapToCourseRecord() {
         Course course = new Course("Computer Science", "4", Course.CourseTypeEnum.ENGINEERING);
         course.setCourseId( new BigDecimal(4));
         CourseRecord record = Mappers.INSTANCE.courseToCourseRecord(course);
         log.debug("Got {}", record);
     }

     @Test
     void  shouldMapRecordToCourse() {
         CourseRecord record = new CourseRecord();
         record.setCourseName("XCourseName");
         record.setCourseDuration(4);
         record.setCourseId(4);
         record.setCourseType(Course.CourseTypeEnum.ENGINEERING.getValue() );

         Course course = Mappers.INSTANCE.courseRecordToCourse(record);
         log.debug("Got {}", course);
     }
}
