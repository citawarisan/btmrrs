DROP
DATABASE IF EXISTS Core; -- cleanup your mess

CREATE
DATABASE Core;
USE
Core;


-- user
CREATE TABLE User
(
    user         VARCHAR(255) PRIMARY KEY,
    email        VARCHAR(255),
    name         VARCHAR(255),
    phone_number VARCHAR(255),
    type         INT CHECK (type = 1 OR type = 2 OR type = 3)
);


-- generic reservation
CREATE TABLE Room
(
    room_id   VARCHAR(255) PRIMARY KEY,
    room_name VARCHAR(255),
    room_size INT,
    faculty   INT,
    FOREIGN KEY (faculty) REFERENCES Faculty (faculty_id)
);

CREATE TABLE Reservation
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    start_datetime DATETIME,
    end_datetime   DATETIME,
    user           VARCHAR(255) UNIQUE,
    room           VARCHAR(255) UNIQUE,
    seats          INT,
    res_status     ENUM('pending', 'success', 'cancelled'),
    details        VARCHAR(255),
    FOREIGN KEY (user) REFERENCES User (user),
    FOREIGN KEY (room) REFERENCES Room (room_id)
);


-- exam related
CREATE TABLE Faculty
(
    faculty_id   INT PRIMARY KEY,
    faculty_name VARCHAR(255)
);

CREATE TABLE Course
(
    course_code        VARCHAR(255) PRIMARY KEY,
    course_name        VARCHAR(255),
    group_number       INT,
    number_of_students INT,
    exam_hours         INT DEFAULT 2
);