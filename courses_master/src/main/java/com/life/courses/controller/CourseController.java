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
@RequestMapping("/api/v1/course")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;
    private final String serviceURL = "/api/v1/course";

    @Operation(
            summary = "Лист курсов",
            description = "Лист курсов, сортировка по дате создания"
    )
    @GetMapping("/list/{personId}")
    public List<Course> courseList(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId) {
        log.info("GET запрос к сервису {}/{personId}. С personId = {}.", serviceURL, personId);
        return courseService.courseList(personId);
    }

    @Operation(
            summary = "Получение курса",
            description = "Получение курса по ID"
    )
    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("GET запрос к сервису {}/{courseId}. С personId = {}. courseId = {}.", serviceURL, courseId);
        return courseService.getCourseById(courseId);
    }

    @Operation(
            summary = "Добавление нового курса",
            description = "Добавление нового курса"
    )
    @PostMapping("/{personId}/name/{courseName}")
    public Course addNewCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                               @PathVariable @Parameter(description = "Название курса") String courseName) {
        log.info("POST запрос к сервису {}/{personId}. С personId = {}. Название курса: {}.",
                serviceURL, personId, courseName);
        return courseService.addNewCourse(personId, courseName);
    }

    @Operation(
            summary = "Изменить название курса",
            description = "Меняет название курса на новое"
    )
    @PatchMapping("/{courseId}/name/{courseName}")
    public Course patchCourseName(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                                  @PathVariable @Parameter(description = "Новое название курса") String courseName) {
        log.info("PATCH запрос к сервису {}/{courseId}/name/{courseName}. CourseId = {}. Новое название курса: {}.",
                serviceURL, courseId, courseName);
        return courseService.patchCourseName(courseId, courseName);
    }

    @Operation(
            summary = "Выполнить курс",
            description = "Изменяет статус курса на DONE"
    )
    @PatchMapping("/{courseId}/done")
    public Course doneCourse(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("PATCH запрос к сервису {}/{courseId}/done. С courseId = {}.",
                serviceURL, courseId);
        return courseService.doneCourse(courseId);
    }

    @Operation(
            summary = "Удаление курса",
            description = "Удаляет курс и связанные уроки по id"
    )
    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("DELETE запрос к сервису {}/{courseId}. С personId = {}. courseId = {}.",
                serviceURL, courseId);
        courseService.deleteCourseById(courseId);
    }

    @Operation(
            summary = "Удаление всех курсов",
            description = "Удаляет все курсы текущего пользователя"
    )
    @DeleteMapping("/all/{personId}")
    public void deleteAllCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId) {
        log.info("DELETE запрос к сервису {}/all/{personId}. С personId = {}.", serviceURL, personId);
        courseService.deleteAllCourse(personId);
    }
}
