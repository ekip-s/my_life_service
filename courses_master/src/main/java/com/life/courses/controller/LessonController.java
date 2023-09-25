package com.life.courses.controller;

import com.life.courses.service.LessonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/lesson")
@Tag(name="LessonController", description="Управление уроками внутри курса")
public class LessonController {

    private final LessonService lessonService;
}