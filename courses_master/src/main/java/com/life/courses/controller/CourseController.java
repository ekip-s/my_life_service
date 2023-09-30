package com.life.courses.controller;

import com.life.courses.service.CourseService;
import com.life.model.courses.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/{personId}/course")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;
    private final String serviceURL = "/v1/{personId}/course";

    @Operation(
            summary = "Лист курсов",
            description = "Лист курсов, сортировка по дате создания"
    )
    @GetMapping
    public List<Course> courseList(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId) {
        return courseService.courseList(personId);
    }

/*
    @Operation(
            summary = "Создание курса",
            description = "Создает новый курс"
    )
    @PostMapping
    public Course createCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") Long personId,
                               @RequestBody Course course) {
        log.info("POST запрос к сервису {}. personId = {}. Параметры запроса: {}.", serviceURL,
                personId, course.toString());
        return courseService.createCourse(personId, course);
    }


    public Course patchCourse(Long personId, Long course_id, Course course) {
        return null;
    }

    public Course getCourseById(Long personId, Long course_id) {
        return null;
    }

    public List<Course> courseList(Long personId) {
        return null;
    }

    public void deleteCourse(Long personId, Long course_id) {

    }

 */
}
