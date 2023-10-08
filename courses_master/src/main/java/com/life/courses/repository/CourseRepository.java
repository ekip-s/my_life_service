package com.life.courses.repository;


import com.life.model.courses.Course;
import com.life.model.courses.Status;
import com.life.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findAllByPersonAndStatusOrderByCreateDT(Person person, Status status);
    void deleteAllByPerson(Person person);
}