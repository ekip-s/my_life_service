package com.life.courses.service;

import com.life.model.courses.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonService {

    Lesson getLessonById(UUID personId, UUID courseId, UUID lessonId);
    Lesson addNewLesson(UUID personId, UUID courseId, Lesson lesson);
    Lesson patchLessonName(UUID personId, UUID courseId, UUID lessonId, String newName);
    Lesson doneLesson(UUID personId, UUID courseId, UUID lessonId);
    void deleteLessonById(UUID personId, UUID courseId, UUID lessonId);
    void deleteLessonByCourse(UUID personId, UUID courseId);
}
