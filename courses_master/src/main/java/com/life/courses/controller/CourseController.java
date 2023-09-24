package com.life.courses.controller;

import com.life.courses.service.CourseService;
import com.life.model.courses.Course;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/course")
@Tag(name="CourseController", description="Управление курсами")
public class CourseController {

    private final CourseService courseService;

    public Course createCourse(Long personId, Course course) {
        return null;
    }

    public Course patchCourse(Long personId, Long course_id, Course course) {
        return null;
    }

    public Course getCourseById(Long personId, Long course_id) {
        return null;
    }

    public List<Course> courseList(Long personId) {
        return null;
    }

    public void deleteCourse(Long personId, Long course_id) {

    }
}
