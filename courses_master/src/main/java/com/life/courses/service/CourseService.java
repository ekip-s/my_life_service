package com.life.courses.service;

import com.life.model.courses.Course;

import java.util.List;

public interface CourseService {

    List<Course> courseList(Long personId);
    Course createCourse(Long personId, Course course);
}
