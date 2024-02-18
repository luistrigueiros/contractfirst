package com.example.contractfirst;

import com.example.contractfirst.domain.CourseRecord;
import com.example.contractfirst.domain.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

@Slf4j
@Component
public class SeedDataInitializer {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    @Value("classpath:/COURSE_RECORD.csv")
    private Resource courseCsv;

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() throws Exception {
        log.info("Creating seed data ....");
        int count = loadFromCsvFile(courseCsv.getFile(), this::processCourseLine);
        log.info("Loaded {} courses", count);
    }

    private int loadFromCsvFile(File file, Consumer<CSVRecord> consumer) throws IOException {
        int count = 0;
        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord line : csvParser) {
                consumer.accept(line);
                count++;
            }
        }
        return count;
    }

    private void processCourseLine(CSVRecord record) {
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseId(Integer.valueOf(record.get("COURSE_ID")));
        courseRecord.setCourseDuration(Integer.valueOf(record.get("COURSE_DURATION")));
        courseRecord.setCourseName(record.get("COURSE_NAME"));
        courseRecord.setCourseType(record.get("COURSE_TYPE"));
        log.debug("About to load {}", courseRecord);
        courseRepository.save(courseRecord);
    }
}
