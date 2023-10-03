package com.life.courses.service;


import com.life.courses.repository.LessonRepository;
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
    @Transactional
    public Lesson addNewLesson(UUID personId, UUID courseId, Lesson lesson) {
        Course course = courseService.getCourseById(personId, courseId);
        List<Lesson> lessons = getLastLessons(course);
        //вытащить все курсы на последнюю дату
        i++;
        LocalDate fakeDate = LocalDate.now().plusDays((int) (Math.random() * 6));
        return lessonRepository.save(lesson.createNewLesson(course, fakeDate, i));
    }

    private List<Lesson> getLastLessons(Course course) {
        return lessonRepository.getLastLessons(course);
    }
}
