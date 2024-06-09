CREATE TABLE email_templates (
    id INTEGER NOT NULL,
    text TEXT,

    CONSTRAINT pk_email_templates PRIMARY KEY (id)
);

CREATE TABLE person_data (
    id INTEGER NOT NULL,
    name CHARACTER VARYING(128) NOT NULL,
    date_of_birth DATE NOT NULL,
    country CHARACTER VARYING(128) NOT NULL,
    language_id INTEGER NOT NULL,

    CONSTRAINT pk_person_data PRIMARY KEY (id),
    CONSTRAINT fk_person_data FOREIGN KEY (language_id) REFERENCES email_templates (id)
);

CREATE TABLE cars (
    id INTEGER NOT NULL,
    brand CHARACTER VARYING(32) NOT NULL,
    type CHARACTER VARYING(32) NOT NULL,
    plate_number CHARACTER VARYING(16) NOT NULL,
    year_of_manufacture INTEGER NOT NULL,
    calculated_value INTEGER NOT NULL,
    driven_distance INTEGER NOT NULL,
    is_sent SMALLINT NOT NULL,

    CONSTRAINT pk_cars PRIMARY KEY (id)
);

CREATE TABLE cars_of_people (
    person_id INTEGER NOT NULL,
    car_id INTEGER NOT NULL,

    CONSTRAINT pk_cars_of_people PRIMARY KEY (person_id, car_id),
    CONSTRAINT fk_cars_of_people_person_id FOREIGN KEY (person_id) REFERENCES person_data (id),
    CONSTRAINT fk_cars_of_people_car_id FOREIGN KEY (car_id) REFERENCES cars (id)
);
