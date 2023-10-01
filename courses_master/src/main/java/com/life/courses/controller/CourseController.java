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
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course/{personId}")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;
    private final String serviceURL = "/v1/course/{personId}";

    @Operation(
            summary = "Лист курсов",
            description = "Лист курсов, сортировка по дате создания"
    )
    @GetMapping
    public List<Course> courseList(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId) {
        log.info("GET запрос к сервису {}. С personId = {}.", serviceURL, personId);
        return courseService.courseList(personId);
    }

    @Operation(
            summary = "Добавление нового курса",
            description = "Добавление нового курса"
    )
    @PostMapping
    public Course addNewCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                               @RequestBody Course course) {
        log.info("POST запрос к сервису {}. С personId = {}. Параметры запроса: {}.",
                serviceURL, personId, course.toString());
        return courseService.addNewCourse(personId, course);
    }

    @Operation(
            summary = "Удаление курса",
            description = "Удаляет курс и связанные уроки по id"
    )
    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                  @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("DELETE запрос к сервису {}. С personId = {}. courseId = {}.",
                serviceURL, personId, courseId);
        courseService.deleteCourseById(personId, courseId);
    }
}
