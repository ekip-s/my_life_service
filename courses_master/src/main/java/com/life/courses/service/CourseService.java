package com.life.courses.service;

import com.life.model.courses.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<Course> courseList(UUID personId);
    Course getCourseById(UUID personId, UUID courseId);
    Course addNewCourse(UUID personId, Course course);
    void deleteCourseById(UUID personId, UUID courseId);
    Course patchCourseName(UUID personId, UUID courseId, Course course);
    Course doneCourse(UUID personId, UUID courseId);
    void deleteAllCourse(UUID personId);
}
