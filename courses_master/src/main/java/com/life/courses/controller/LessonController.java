package com.life.courses.controller;


import com.life.courses.service.LessonService;
import com.life.model.courses.Lesson;
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
@RequestMapping("/api/v1/lesson")
@Tag(name="Lesson Controller", description="Управление уроками внутри курса")
public class LessonController {

    private final LessonService lessonService;
    private final String serviceURL = "/api/v1/lesson";

    @Operation(
            summary = "Получение урока по id",
            description = "Получение урока по id"
    )
    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("GET запрос к сервису {}/{lessonId}. С lessonId = {}.",
                serviceURL, lessonId);
        return lessonService.getLessonById(lessonId);
    }

    @Operation(
            summary = "Лист уроков",
            description = "Лист уроков по курсу"
    )
    @GetMapping("/list/{courseId}")
    public List<Lesson> getLessonList(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("GET запрос к сервису {}/list/{courseId}. С courseId = {}.",
                serviceURL, courseId);
        return lessonService.getLessonList(courseId);
    }

    @Operation(
            summary = "Добавление урока",
            description = "Добавление урока к курсу"
    )
    @PostMapping("/person/{personId}/course/{courseId}/name/{lessonName}")
    public Lesson addNewLesson(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                               @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                               @PathVariable @Parameter(description = "Название курса") String lessonName) {
        log.info("POST запрос к сервису {}/person/{personId}/course/{courseId}/name/{lessonName}." +
                        " personId = {}, courseId = {}. Название курса: {}.",
                serviceURL, personId, courseId, lessonName);
        return lessonService.addNewLesson(personId, courseId, lessonName);
    }

    @Operation(
            summary = "Обновить название урока",
            description = "Обновить название урока"
    )
    @PatchMapping("/setName/{lessonId}/name/{newName}")
    public Lesson patchLessonName(@PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId,
                                  @PathVariable @Parameter(description = "Новое название урока") String newName) {
        log.info("PATCH запрос к сервису {}/setName/{lessonId}/name/{newName}. " +
                        "C lessonId = {}. Новое название курса: {}.",
                serviceURL,lessonId, newName);
        return lessonService.patchLessonName(lessonId, newName);
    }

    @Operation(
            summary = "Урок выполнен",
            description = "Меняет статус урока на DONE, если урок последний в курсе, выполнится и он"
    )
    @PatchMapping("/done/{lessonId}")
    public Lesson doneLesson(@PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("PATCH запрос к сервису {}/done/{lessonId}. C lessonId = {}.",
                serviceURL, lessonId);
        return lessonService.doneLesson(lessonId);
    }

    @Operation(
            summary = "Удаление урока по id",
            description = "Удаляет урок по id"
    )
    @DeleteMapping("/{lessonId}")
    public void deleteLessonById(@PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("DELETE запрос к сервису {}. C lessonId = {}.",
                serviceURL, lessonId);
        lessonService.deleteLessonById(lessonId);
    }

    @Operation(
            summary = "Удаление уроков в курсе",
            description = "Удаляет все уроки в курсе"
    )
    @DeleteMapping("/all/{courseId}")
    public void deleteLessonByCourse(@PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("DELETE запрос к сервису {}/all/{courseId}. C courseId = {}.",
                serviceURL, courseId);
        lessonService.deleteLessonByCourse(courseId);
    }
}
