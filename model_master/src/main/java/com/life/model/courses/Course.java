package com.life.model.courses;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.life.model.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "create_dt")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime createDT;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Transient
    @OneToMany(mappedBy="course", fetch = FetchType.LAZY)
    private List<Lesson> lessonList;

    public Course(UUID personId, String courseName) {
        this.person = new Person(personId);
        this.courseName = courseName;
        this.createDT = LocalDateTime.now();
        this.status = Status.NEW;
    }

    public Course(UUID id) {
        this.id = id;
    }

    public Course doneCourse() {
        this.endDate = LocalDate.now();
        this.status = Status.DONE;
        return this;
    }
}
