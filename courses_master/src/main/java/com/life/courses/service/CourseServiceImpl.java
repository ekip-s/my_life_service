package com.life.courses.service;

import com.life.client.client.PersonClient;
import com.life.courses.repository.CourseRepository;
import com.life.exception.NotFoundException;
import com.life.model.courses.Course;
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
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(UUID personId, UUID courseId) {
        checkPerson(personId);
        return getCourseById(courseId);
    }

    @Override
    @Transactional
    public Course addNewCourse(UUID personId, Course course) {
        checkPerson(personId);
        course.newCourse(personId);
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(UUID personId, UUID courseId) {
        checkPerson(personId);
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional
    public Course patchCourseName(UUID personId, UUID courseId, Course course) {
        checkPerson(personId);
        Course oldCourse = getCourseById(courseId);
        oldCourse.setCourseName(course.getCourseName());
        return courseRepository.save(oldCourse);
    }

    @Override
    @Transactional
    public void deleteAllCourse(UUID personId) {
        courseRepository.deleteAllByPerson(new Person(personId));
    }

    private void checkPerson(UUID personId) {
        personClient.getPersonByIdSync(personId);
    }

    @Transactional
    private Course getCourseById(UUID courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Некорректный запрос",
                        "Нет курса с id: " + courseId + "."));
    }
}
