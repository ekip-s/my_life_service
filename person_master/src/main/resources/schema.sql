CREATE TABLE IF NOT EXISTS Person (
    id uuid DEFAULT gen_random_uuid(),
    login varchar(50) UNIQUE NOT NULL,
    password varchar(200) NOT NULL,
    create_dt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS person_login_index ON Person (login);

INSERT INTO Person (login, password, create_dt)
VALUES ('test_login', 'test_password', current_date)
ON CONFLICT DO NOTHING;