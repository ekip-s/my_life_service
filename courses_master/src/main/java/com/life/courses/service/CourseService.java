package com.life.courses.service;

import com.life.model.courses.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<Course> courseList(UUID personId);
    Course addNewCourse(UUID personId, Course course);
    void deleteCourseById(UUID personId, UUID courseId);
}
