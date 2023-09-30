package com.life.courses.service;


import com.life.courses.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
}
