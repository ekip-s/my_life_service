package com.life.courses.service;

import com.life.model.courses.Lesson;

import java.util.UUID;

public interface LessonService {

    Lesson addNewLesson(UUID personId, UUID courseId, Lesson lesson);
}
