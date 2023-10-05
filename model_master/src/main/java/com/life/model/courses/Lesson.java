package com.life.model.courses;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Lessons")
public class Lesson {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="courses_id", nullable=false)
    private Course course;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "planed_start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate planedStartDate;
    @Column(name = "lesson_num")
    private Integer lessonNum;
    @Column(name = "start_dt")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startDT;
    @Column(name = "end_dt")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endDT;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Lesson createNewLesson(Course course, LocalDate planedStartDate, Integer lessonNum) {
        this.course = course;
        this.planedStartDate = planedStartDate;
        this.lessonNum = lessonNum;
        this.startDT = LocalDateTime.now();
        this.status = Status.NEW;
        return this;
    }

    public Lesson doneLesson() {
        this.endDT = LocalDateTime.now();
        this.status = Status.DONE;
        return this;
    }
}
