package com.life.courses.controller;


import com.life.courses.service.CourseService;
import com.life.model.courses.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;
    private final String serviceURL = "/v1/course/{personId}";

    @Operation(
            summary = "Лист курсов",
            description = "Лист курсов, сортировка по дате создания"
    )
    @GetMapping
    public List<Course> courseList(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId) {
        return courseService.courseList(personId);
    }
}
