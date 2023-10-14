package com.life.courses.service;


import com.life.client.client.PersonClient;
import com.life.courses.repository.LessonRepository;
import com.life.exception.NotFoundException;
import com.life.model.courses.Course;
import com.life.model.courses.Lesson;
import com.life.model.courses.Status;
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
    private final PersonClient personClient;
    int i = 0;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseService courseService, PersonClient personClient) {
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
        this.personClient = personClient;
    }

    @Override
    public Lesson getLessonById(UUID lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                "Нет урока с id: " + lessonId + "."));
    }

    @Override
    public List<Lesson> getLessonList(UUID courseId) {
        Course course = courseService.getCourseById(courseId);
        return lessonRepository.findAllByCourseAndStatusOrderByLessonNum(new Course(courseId), Status.NEW);
    }

    @Override
    @Transactional
    public Lesson addNewLesson(UUID personId, UUID courseId, String lessonName) {
        checkPerson(personId);
        Course course = courseService.getCourseById(courseId);
        List<Lesson> lessons = getLastLessons();
        LocalDate planedStartDate;

        if(lessons.isEmpty()) {
            planedStartDate = LocalDate.now();
        } else if (lessons.size() < 3) {
            Lesson engLesson = lessons.get(lessons.size() - 1);
            planedStartDate = engLesson.getPlanedStartDate();
        } else {
            Lesson engLesson = lessons.get(lessons.size()- 1);
            planedStartDate = engLesson.getPlanedStartDate().plusDays(1);
        }
        Integer lessonNum;
        List<Lesson> lessonByCourse = lessonRepository.findAllByCourse(course);
        if(lessonByCourse.isEmpty()) {
            lessonNum = 1;
        } else {
            lessonNum = lessonByCourse.size() + 1;
        }


        return lessonRepository.save(new Lesson(course, planedStartDate, lessonNum, lessonName));
    }

    @Override
    @Transactional
    public Lesson patchLessonName(UUID lessonId, String newName) {
        Lesson lesson = getLessonById(lessonId);
        lesson.setLessonName(newName);
        return lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public Lesson doneLesson(UUID lessonId) {
        Lesson lesson = getLessonById(lessonId);
        return lessonRepository.save(lesson.doneLesson());
    }

    @Override
    @Transactional
    public void deleteLessonById(UUID lessonId) {
        getLessonById(lessonId);
        lessonRepository.deleteById(lessonId);
    }

    @Override
    @Transactional
    public void deleteLessonByCourse(UUID courseId) {
        Course course = courseService.getCourseById(courseId);
        lessonRepository.deleteAllByCourse(course);
    }

    private void checkPerson(UUID personId) {
        personClient.getPersonByIdSync(personId);
    }

    private List<Lesson> getLastLessons() {
        return lessonRepository.getLastLessons();
    }
}
