package com.life.model.courses;

import com.life.model.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToMany(mappedBy="course")
    private List<Lesson> lessonList;

    public void newCourse(Long personId) {
        this.person = new Person(personId);
        this.createDT = LocalDateTime.now();
        this.status = Status.NEW;
    }
}
