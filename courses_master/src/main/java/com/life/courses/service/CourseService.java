package com.life.courses.service;

import com.life.model.courses.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<Course> courseList(UUID personId);
    Course getCourseById(UUID courseId);
    Course addNewCourse(UUID personId, String courseName);
    void deleteCourseById(UUID courseId);
    Course patchCourseName(UUID courseId, String courseName);
    Course doneCourse(UUID courseId);
    void deleteAllCourse(UUID personId);
}
