package com.life.courses.service;

import com.life.client.client.PersonClient;
import com.life.courses.repository.CourseRepository;
import com.life.exception.NotFoundException;
import com.life.model.courses.Course;
import com.life.model.courses.Status;
import com.life.model.person.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PersonClient personClient;


    public CourseServiceImpl(CourseRepository courseRepository, PersonClient personClient) {
        this.courseRepository = courseRepository;
        this.personClient = personClient;
    }


    @Override
    public List<Course> courseList(UUID personId) {
        checkPerson(personId);
        return courseRepository.findAllByPersonAndStatusOrderByCreateDT(new Person(personId), Status.NEW);
    }

    @Override
    @Transactional
    public Course addNewCourse(UUID personId, String courseName) {
        checkPerson(personId);
        return courseRepository.save(new Course(personId, courseName));
    }

    @Override
    @Transactional
    public void deleteCourseById(UUID courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional
    public Course patchCourseName(UUID courseId, String courseName) {
        Course oldCourse = getCourseById(courseId);
        oldCourse.setCourseName(courseName);
        return courseRepository.save(oldCourse);
    }

    @Override
    @Transactional
    public Course doneCourse(UUID courseId) {
        Course course = getCourseById(courseId);
        return courseRepository.save(course.doneCourse());
    }

    @Override
    @Transactional
    public void deleteAllCourse(UUID personId) {
        courseRepository.deleteAllByPerson(new Person(personId));
    }

    private void checkPerson(UUID personId) {
        personClient.getPersonByIdSync(personId);
    }

    @Override
    public Course getCourseById(UUID courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                        "Нет курса с id: " + courseId + "."));
    }

    @Transactional
    public void setCourseDate() {

    }
}