package com.life.courses.repository;


import com.life.model.courses.Course;
import com.life.model.courses.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query("SELECT b from Lesson b " +
           " WHERE b.planedStartDate = " +
            " (select planedStartDate from Lesson order by planedStartDate desc limit 1) " +
            " order by planedStartDate")
    List<Lesson> getLastLessons();

    Optional<Lesson> getLessonByCourseAndId(@Param("course") Course course, @Param("id") UUID id);
}