package com.life.courses.service;

import com.life.model.courses.Lesson;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {

    Lesson getLessonById(UUID lessonId);
    List<Lesson> getLessonList(UUID courseId);
    Lesson addNewLesson(UUID personId, UUID courseId, String lessonName);
    Lesson patchLessonName(UUID lessonId, String newName);
    Lesson doneLesson(UUID lessonId);
    void deleteLessonById(UUID lessonId);
    void deleteLessonByCourse(UUID courseId);
}
