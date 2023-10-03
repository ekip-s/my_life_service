package com.life.courses.controller;


import com.life.courses.service.LessonService;
import com.life.model.courses.Lesson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
            summary = "Добавление урока",
            description = "Добавление урока к курсу"
    )
    @PostMapping
    public Lesson addNewLesson(@PathVariable @Parameter(description = "Идентификатор пользователя") UUID personId,
                               @PathVariable @Parameter(description = "Идентификатор пользователя") UUID courseId,
                               @RequestBody Lesson lesson) {
        log.info("POST запрос к сервису {}. personId = {}, courseId = {}. Параметры запроса: {}.",
                serviceURL, personId, courseId, lesson.toString());
        return lessonService.addNewLesson(personId, courseId, lesson);
    }


}
