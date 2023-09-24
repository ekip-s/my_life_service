CREATE TABLE IF NOT EXISTS Courses (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    person_id bigint NOT NULL,
    course_name varchar(100) NOT NULL,
    create_dt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    start_date DATE,
    end_date DATE,
    status SMALLINT NOT NULL,
    CONSTRAINT pk_courses PRIMARY KEY (id),
    CONSTRAINT fk_courses_person FOREIGN KEY (person_id) REFERENCES person ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS courses_fk_index ON Courses (person_id);

CREATE TABLE IF NOT EXISTS Lessons (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    courses_id bigint NOT NULL,
    lesson_name varchar(100) not null,
    planed_start_date DATE,
    start_dt TIMESTAMP WITHOUT TIME ZONE,
    end_dt TIMESTAMP WITHOUT TIME ZONE,
    status SMALLINT NOT NULL,
    CONSTRAINT pk_lessons PRIMARY KEY (id),
    CONSTRAINT fk_lessons_courses FOREIGN KEY (courses_id) REFERENCES Courses ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS lessons_fk_index ON Lessons (courses_id);