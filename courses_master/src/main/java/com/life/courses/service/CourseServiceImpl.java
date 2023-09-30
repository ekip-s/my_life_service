package com.life.courses.service;

import com.life.model.courses.Course;
import com.life.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> courseList(Long personId) {
        checkPerson(personId);
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Long personId, Course course) {

        return null;
    }

    private void checkPerson(Long personId) {

    }
}
