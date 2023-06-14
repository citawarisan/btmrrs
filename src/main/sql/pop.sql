-- Insert dummy data into User table
INSERT INTO User (user, password, type, name, email, phone_number)
VALUES
    ('user1', 'password1', 1, 'John Doe', 'john@example.com', '1234567890'),
    ('user2', 'password2', 2, 'Jane Smith', 'jane@example.com', '9876543210'),
    ('user3', 'password3', 1, 'David Johnson', 'david@example.com', '5555555555');

-- Insert dummy data into Faculty table
INSERT INTO Faculty (faculty_id, faculty_name)
VALUES
    (1, 'Faculty of Science'),
    (2, 'Faculty of Arts'),
    (3, 'Faculty of Engineering');

-- Insert dummy data into Room table
INSERT INTO Room (room_id, room_name, room_size, faculty)
VALUES
    ('room1', 'Lecture Hall 101', 100, 1),
    ('room2', 'Conference Room A', 20, 2),
    ('room3', 'Lab Room 305', 30, 3);

-- Insert dummy data into Reservation table
INSERT INTO Reservation (user, room, seats, start_datetime, end_datetime, details, status)
VALUES
    ('user1', 'room1', 50, '2023-06-15 10:00:00', '2023-06-15 12:00:00', 'Meeting with clients', 'success'),
    ('user2', 'room2', 10, '2023-06-16 14:00:00', '2023-06-16 16:00:00', 'Team brainstorming session', 'pending'),
    ('user3', 'room3', 20, '2023-06-17 09:00:00', '2023-06-17 11:00:00', 'Training session', 'success');

-- Insert dummy data into Course table
INSERT INTO Course (course_code, course_name, group_number, number_of_students, exam_hours)
VALUES
    ('CS101', 'Introduction to Computer Science', 1, 100, 2),
    ('ENG201', 'Advanced English', 2, 80, 3),
    ('MATH301', 'Calculus III', 1, 120, 2);

-- Insert dummy data into User_Courses table
INSERT INTO User_Courses (course_code, user)
VALUES
    ('CS101', 'user1'),
    ('ENG201', 'user2'),
    ('MATH301', 'user3');
