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
@RequestMapping("/api/v1/{personId}/lesson/{courseId}")
@Tag(name="Lesson Controller", description="Управление уроками внутри курса")
public class LessonController {

    private final LessonService lessonService;
    private final String serviceURL = "/api/v1/{personId}/lesson/{courseId}";

    @Operation(
            summary = "Получение урока по id",
            description = "Получение урока по id"
    )
    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                                @PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("GET запрос к сервису {}. personId = {}, courseId = {}, lessonId = {}.",
                serviceURL, personId, courseId, lessonId);
        return lessonService.getLessonById(personId, courseId, lessonId);
    }

    @Operation(
            summary = "Добавление урока",
            description = "Добавление урока к курсу"
    )
    @PostMapping
    public Lesson addNewLesson(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                               @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                               @RequestBody Lesson lesson) {
        log.info("POST запрос к сервису {}. personId = {}, courseId = {}. Параметры запроса: {}.",
                serviceURL, personId, courseId, lesson.toString());
        return lessonService.addNewLesson(personId, courseId, lesson);
    }

    @Operation(
            summary = "Обновить название урока",
            description = "Обновить название урока"
    )
    @PatchMapping("/setName/{newName}")
    public Lesson patchLessonName(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                  @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                                  @PathVariable @Parameter(description = "Идентификатор курса") String newName) {
        log.info("PATCH запрос к сервису {}/setName. personId = {}, courseId = {}. Новое название курса: {}.",
                serviceURL, personId, courseId, newName);
        return lessonService.patchLessonName(personId, courseId, newName);
    }

    @Operation(
            summary = "Урок выполнен",
            description = "Меняет статус урока на DONE, если урок последний в курсе, выполнится и он"
    )
    @PatchMapping("/done/{lessonId}")
    public Lesson doneLesson(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                             @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                             @PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("PATCH запрос к сервису {}/done. personId = {}, courseId = {}, lessonId = {}.",
                serviceURL, personId, courseId, lessonId);
        return lessonService.doneLesson(personId, courseId, lessonId);
    }

    @Operation(
            summary = "Удаление урока по id",
            description = "Удаляет урок по id"
    )
    @DeleteMapping("/{lessonId}")
    public void deleteLessonById(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                 @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId,
                                 @PathVariable @Parameter(description = "Идентификатор урока") UUID lessonId) {
        log.info("DELETE запрос к сервису {}. personId = {}, courseId = {}, lessonId = {}.",
                serviceURL, personId, courseId, lessonId);
        lessonService.deleteLessonById(personId, courseId, lessonId);
    }

    @Operation(
            summary = "Удаление уроков в курсе",
            description = "Удаляет все уроки в курсе"
    )
    @DeleteMapping("/all")
    public void deleteLessonByCourse(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                                     @PathVariable @Parameter(description = "Идентификатор курса") UUID courseId) {
        log.info("DELETE запрос к сервису {}. personId = {}, courseId = {}.",
                serviceURL, personId, courseId);
        lessonService.deleteLessonByCourse(personId, courseId);
    }
}
