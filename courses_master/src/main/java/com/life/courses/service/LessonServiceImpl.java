package com.life.courses.service;


import com.life.courses.repository.LessonRepository;
import com.life.exception.NotFoundException;
import com.life.model.courses.Course;
import com.life.model.courses.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseService courseService;
    int i = 0;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseService courseService) {
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
    }

    @Override
    public Lesson getLessonById(UUID personId, UUID courseId, UUID lessonId) {
        Course course = courseService.getCourseById(personId, courseId);
        return lessonRepository.getLessonByCourseAndId(course, lessonId)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                "Нет урока с id: " + lessonId + "."));
    }

    @Override
    @Transactional
    public Lesson addNewLesson(UUID personId, UUID courseId, Lesson lesson) {
        Course course = courseService.getCourseById(personId, courseId);
        List<Lesson> lessons = getLastLessons();
        LocalDate planedStartDate;
        Integer lessonNum;
        if(lessons.isEmpty()) {
            planedStartDate = LocalDate.now();
            lessonNum = 1;
        } else if (lessons.size() < 3) {
            Lesson engLesson = lessons.get(lessons.size() - 1);
            planedStartDate = engLesson.getPlanedStartDate();
            lessonNum = engLesson.getLessonNum() + 1;
        } else {
            Lesson engLesson = lessons.get(lessons.size()- 1);
            planedStartDate = engLesson.getPlanedStartDate().plusDays(1);
            lessonNum = engLesson.getLessonNum() + 1;
        }

        return lessonRepository.save(lesson.createNewLesson(course, planedStartDate, lessonNum));
    }

    @Override
    public Lesson patchLessonName(UUID personId, UUID courseId, String newName) {
        return null;
    }

    @Override
    public Lesson doneLesson(UUID personId, UUID courseId, UUID lessonId) {
        return null;
    }

    @Override
    public void deleteLessonById(UUID personId, UUID courseId, UUID lessonId) {

    }

    @Override
    public void deleteLessonByCourse(UUID personId, UUID courseId) {

    }

    private List<Lesson> getLastLessons() {
        return lessonRepository.getLastLessons();
    }
}
