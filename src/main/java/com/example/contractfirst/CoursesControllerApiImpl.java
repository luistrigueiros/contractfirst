package com.example.contractfirst;

import com.example.openapi.CoursesApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${app.apiRoot}")
public class CoursesControllerApiImpl implements CoursesApi {
}
