package com.life.courses.repository;


import com.life.model.courses.Course;
import com.life.model.courses.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query("SELECT b from Lesson b " +
           " WHERE b.course=:course and b.planedStartDate = " +
            " (select planedStartDate from Lesson order by lessonNum desc limit 1) " +
            " order by lessonNum")
    List<Lesson> getLastLessons(@Param("course") Course course);
}