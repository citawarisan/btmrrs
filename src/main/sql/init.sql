-- cleanup your mess
DROP
DATABASE IF EXISTS BTMRRS;

CREATE
DATABASE BTMRRS;

USE
BTMRRS;


-- user
CREATE TABLE User
(
    user         VARCHAR(255) PRIMARY KEY,
    password     VARCHAR(255),
    type         INT,
    name         VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255)
);

-- generic reservation
CREATE TABLE Faculty
(
    faculty_id   INT PRIMARY KEY,
    faculty_name VARCHAR(255)
);

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
    user           VARCHAR(255) UNIQUE,
    room           VARCHAR(255) UNIQUE,
    seats          INT,
    start_datetime DATETIME,
    end_datetime   DATETIME,
    details        VARCHAR(255),
    status         ENUM('pending', 'success', 'cancelled'),
    FOREIGN KEY (user) REFERENCES User (user),
    FOREIGN KEY (room) REFERENCES Room (room_id)
);

-- exam reservation
CREATE TABLE Course
(
    course_code        VARCHAR(255) PRIMARY KEY,
    course_name        VARCHAR(255),
    group_number       INT,
    number_of_students INT,
    exam_hours         INT DEFAULT 2
);

CREATE TABLE User_Courses
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(255) NOT NULL,
    user        VARCHAR(255) NOT NULL,
    FOREIGN KEY (course_code) REFERENCES Course (course_code),
    FOREIGN KEY (user) REFERENCES User (user)
);

-- views
CREATE VIEW Reservation_View AS
SELECT
    r.id,
    r.user,
    r.start_datetime AS date,
    CONCAT(DATE_FORMAT(r.start_datetime, '%H%i'), ' - ', DATE_FORMAT(r.end_datetime, '%H%i')) AS time,
    CONCAT(f.faculty_name, ' - ', ro.room_name) AS room,
    r.details,
    r.status
FROM Reservation r
    JOIN Room ro ON r.room = ro.room_id
    JOIN Faculty f ON ro.faculty = f.faculty_id;
