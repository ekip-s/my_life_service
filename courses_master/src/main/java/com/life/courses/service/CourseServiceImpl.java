package com.life.courses.service;

import com.life.client.client.PersonClient;
import com.life.courses.repository.CourseRepository;
import com.life.model.courses.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
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
    public Course addNewCourse(UUID personId, Course course) {
        checkPerson(personId);
        course.newCourse(personId);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(UUID personId, UUID courseId) {
        checkPerson(personId);
        courseRepository.deleteById(courseId);
    }

    private void checkPerson(UUID personId) {
        personClient.getPersonByIdSync(personId);
    }
}
