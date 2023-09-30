package com.life.courses.service;

import com.life.client.client.PersonClient;
import com.life.courses.repository.CourseRepository;
import com.life.model.courses.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PersonClient personClient;


    public CourseServiceImpl(CourseRepository courseRepository, PersonClient personClient) {
        this.courseRepository = courseRepository;
        this.personClient = personClient;
    }


    @Override
    public List<Course> courseList(Long personId) {
        checkPerson(personId);
        return courseRepository.findAll();
    }

    @Override
    public Course addNewCourse(Long personId, Course course) {
        course.newCourse(personId);
        return courseRepository.save(course);
    }

    private void checkPerson(Long personId) {
        personClient.getPersonByIdSync(personId);
    }
}
