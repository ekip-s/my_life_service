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
@RequestMapping("/api/v1/{personId}/course")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;
    private final String serviceURL = "/api/v1/{personId}/course";

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
            summary = "Получение курса",
            description = "Получение курса по ID"
    )
    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("GET запрос к сервису {}. С personId = {}. courseId = {}.",
                serviceURL, personId, courseId);
        return getCourseById(personId, courseId);
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
            summary = "Изменить название курса",
            description = "Меняет название курса на новое"
    )
    @PatchMapping("/{courseId}")
    public Course patchCourseName(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                  @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                               @RequestBody Course course) {
        log.info("DELETE запрос к сервису {}. С personId = {}. courseId = {}. Параметры запроса: {}.",
                serviceURL, personId, courseId, course.toString());
        return courseService.patchCourseName(personId, courseId, course);
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

    @Operation(
            summary = "Удаление всех курсов",
            description = "Удаляет все курсы текущего пользователя"
    )
    @DeleteMapping("/all")
    public void deleteAllCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId) {
        log.info("DELETE запрос к сервису {}/all. С personId = {}.",
                serviceURL, personId);
        courseService.deleteAllCourse(personId);
    }
}
