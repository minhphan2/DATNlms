-- PostgreSQL SQL Dump
-- Converted from MySQL (phpMyAdmin) to PostgreSQL
-- Original Generation Time: Dec 27, 2025 at 06:48 AM
-- Original Server version: 8.0.44

-- Database: lms

-- --------------------------------------------------------

--
-- Table structure for table "answers"
--

CREATE TABLE answers (
  answerid SERIAL PRIMARY KEY,
  questionid INTEGER DEFAULT NULL,
  answertext TEXT,
  iscorrect BOOLEAN DEFAULT FALSE
);

-- --------------------------------------------------------

--
-- Table structure for table "assignments"
--

CREATE TABLE assignments (
  assignmentid SERIAL PRIMARY KEY,
  courseid INTEGER DEFAULT NULL,
  title VARCHAR(255) DEFAULT NULL,
  instructions TEXT,
  deadline TIMESTAMP DEFAULT NULL,
  maxscore INTEGER DEFAULT 100,
  createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  attachmenturl VARCHAR(255) DEFAULT NULL
);

--
-- Dumping data for table "assignments"
--

INSERT INTO assignments (assignmentid, courseid, title, instructions, deadline, maxscore, createdat, attachmenturl) VALUES
(1, 1, 'Bài tập 1: Java OOP', NULL, '2025-12-25 23:59:59', 100, '2025-12-18 09:47:02', '/assignments/3aca7887-e19d-4c18-9e89-dd64865e1e4b.pdf'),
(2, 1, 'Bài tập 1: Java OOP', 'Làm bài tập về Kế thừa và Đa hình', '2025-12-25 23:59:59', 100, '2025-12-18 09:47:12', '/assignments/771bdae0-7b3a-45be-b5e3-582338370e0c.pdf');

-- Reset sequence for assignments
SELECT setval('assignments_assignmentid_seq', (SELECT MAX(assignmentid) FROM assignments));

-- --------------------------------------------------------

--
-- Table structure for table "attendance"
--

CREATE TABLE attendance (
  attendance_id SERIAL PRIMARY KEY,
  course_id INTEGER DEFAULT NULL,
  student_id INTEGER DEFAULT NULL,
  date DATE DEFAULT NULL,
  status VARCHAR(20) CHECK (status IN ('present', 'absent', 'late'))
);

-- --------------------------------------------------------

--
-- Table structure for table "chat_messages"
--

CREATE TABLE chat_messages (
  message_id SERIAL PRIMARY KEY,
  sender_id INTEGER DEFAULT NULL,
  receiver_id INTEGER DEFAULT NULL,
  course_id INTEGER DEFAULT NULL,
  message TEXT,
  sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table "courses"
--

CREATE TABLE courses (
  courseid SERIAL PRIMARY KEY,
  teacherid INTEGER DEFAULT NULL,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(500) DEFAULT NULL,
  category VARCHAR(100) DEFAULT NULL,
  thumbnail VARCHAR(300) DEFAULT NULL,
  status VARCHAR(20) CHECK (status IN ('active', 'inactive')) DEFAULT 'active',
  createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  coursename VARCHAR(200) NOT NULL,
  maxstudents INTEGER DEFAULT NULL,
  startdate DATE DEFAULT NULL,
  enddate DATE DEFAULT NULL
);

--
-- Dumping data for table "courses"
--

INSERT INTO courses (courseid, teacherid, title, description, category, thumbnail, status, createdat, coursename, maxstudents, startdate, enddate) VALUES
(1, 5, 'Java Spring Boot Nâng Cao', 'Khóa học nâng cao cho developer', 'Programming', 'thumbnail-advanced.jpg', 'active', '2025-12-16 09:18:44', 'JAVA102', NULL, NULL, NULL),
(2, 3, 'Java Spring Boot Căn Bản', 'Khóa học Java Spring Boot từ đầu cho người mới bắt đầu', 'Programming', 'https://example.com/java-course.jpg', 'active', '2025-12-17 02:39:33', 'java', NULL, NULL, NULL),
(3, 3, 'Java Spring Boot Căn Bản2', 'Khóa học Java Spring Boot từ đầu cho người mới bắt đầu', 'Programming', 'https://example.com/java-course.jpg', 'active', '2025-12-17 03:09:18', 'java', NULL, NULL, NULL),
(4, 3, 'Java Spring Boot Căn Bản', 'Khóa học Java cho người mới', 'Programming', 'java.jpg', 'active', '2025-12-17 06:09:39', 'JAVA101', 1, NULL, NULL);

-- Reset sequence for courses
SELECT setval('courses_courseid_seq', (SELECT MAX(courseid) FROM courses));

-- --------------------------------------------------------

--
-- Table structure for table "enrollments"
--

CREATE TABLE enrollments (
  enrollmentid SERIAL PRIMARY KEY,
  courseid INTEGER DEFAULT NULL,
  studentid INTEGER DEFAULT NULL,
  enrolledat TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Dumping data for table "enrollments"
--

INSERT INTO enrollments (enrollmentid, courseid, studentid, enrolledat) VALUES
(1, 1, 3, '2025-12-17 06:16:02'),
(2, 1, 3, '2025-12-17 06:16:07'),
(3, 4, 3, '2025-12-17 06:16:43'),
(4, 4, 3, '2025-12-17 06:16:45'),
(5, 4, 5, '2025-12-17 06:16:53'),
(6, 4, 2, '2025-12-17 06:42:17');

-- Reset sequence for enrollments
SELECT setval('enrollments_enrollmentid_seq', (SELECT MAX(enrollmentid) FROM enrollments));

-- --------------------------------------------------------

--
-- Table structure for table "gradebook"
--

CREATE TABLE gradebook (
  grade_id SERIAL PRIMARY KEY,
  student_id INTEGER DEFAULT NULL,
  course_id INTEGER DEFAULT NULL,
  assignment_score REAL DEFAULT 0,
  quiz_score REAL DEFAULT 0,
  final_score REAL DEFAULT 0,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table "lessons"
--

CREATE TABLE lessons (
  lessonid SERIAL PRIMARY KEY,
  sectionid INTEGER DEFAULT NULL,
  title VARCHAR(255) DEFAULT NULL,
  content TEXT,
  videourl VARCHAR(255) DEFAULT NULL,
  position INTEGER DEFAULT NULL,
  createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Dumping data for table "lessons"
--

INSERT INTO lessons (lessonid, sectionid, title, content, videourl, position, createdat) VALUES
(1, 1, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 1, '2025-12-18 02:32:30'),
(3, 1, 'Bài 1: Giới thiệu Java', 'Java là ngôn ngữ lập trình hướng đối tượng...', 'https://youtube.com/watch?v=abc123', 2, '2025-12-18 02:35:53'),
(5, 1, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 3, '2025-12-18 02:36:12'),
(6, 10, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 3, '2025-12-18 02:36:39');

-- Reset sequence for lessons
SELECT setval('lessons_lessonid_seq', (SELECT MAX(lessonid) FROM lessons));

-- --------------------------------------------------------

--
-- Table structure for table "materials"
--

CREATE TABLE materials (
  materialid SERIAL PRIMARY KEY,
  lessonid INTEGER DEFAULT NULL,
  filename VARCHAR(255) DEFAULT NULL,
  fileurl VARCHAR(255) DEFAULT NULL,
  uploadedat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  filetype VARCHAR(255) NOT NULL
);

--
-- Dumping data for table "materials"
--

INSERT INTO materials (materialid, lessonid, filename, fileurl, uploadedat, filetype) VALUES
(1, 1, 'Phan Tuấn Minh_CV.pdf.pdf', '/materials/ac8d1fe9-2e4f-4379-9e86-3b655024120c.pdf', '2025-12-18 04:34:16', 'application/pdf');

-- Reset sequence for materials
SELECT setval('materials_materialid_seq', (SELECT MAX(materialid) FROM materials));

-- --------------------------------------------------------

--
-- Table structure for table "notifications"
--

CREATE TABLE notifications (
  notification_id SERIAL PRIMARY KEY,
  user_id INTEGER DEFAULT NULL,
  message TEXT,
  is_read BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table "questions"
--

CREATE TABLE questions (
  questionid SERIAL PRIMARY KEY,
  quizid INTEGER DEFAULT NULL,
  questiontext TEXT,
  type SMALLINT NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "quizzes"
--

CREATE TABLE quizzes (
  quizid SERIAL PRIMARY KEY,
  courseid INTEGER DEFAULT NULL,
  title VARCHAR(255) DEFAULT NULL,
  timelimit INTEGER DEFAULT NULL,
  createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table "quiz_results"
--

CREATE TABLE quiz_results (
  result_id SERIAL PRIMARY KEY,
  quiz_id INTEGER DEFAULT NULL,
  student_id INTEGER DEFAULT NULL,
  score INTEGER DEFAULT NULL,
  taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table "schedules"
--

CREATE TABLE schedules (
  scheduleid SERIAL PRIMARY KEY,
  courseid INTEGER DEFAULT NULL,
  sectionid INTEGER DEFAULT NULL,
  lessonid INTEGER DEFAULT NULL,
  dayofweek SMALLINT NOT NULL,
  starttime VARCHAR(255) NOT NULL,
  endtime VARCHAR(255) NOT NULL,
  startdate TIMESTAMP NOT NULL,
  enddate TIMESTAMP NOT NULL,
  location VARCHAR(255) DEFAULT NULL,
  type SMALLINT NOT NULL
);

--
-- Dumping data for table "schedules"
--

INSERT INTO schedules (scheduleid, courseid, sectionid, lessonid, dayofweek, starttime, endtime, startdate, enddate, location, type) VALUES
(1, 2, 1, 1, 1, '09:00', '11:00', '2025-12-23 09:00:00', '2025-12-23 11:00:00', 'Phòng 102', 1);

-- Reset sequence for schedules
SELECT setval('schedules_scheduleid_seq', (SELECT MAX(scheduleid) FROM schedules));

-- --------------------------------------------------------

--
-- Table structure for table "sections"
--

CREATE TABLE sections (
  sectionid SERIAL PRIMARY KEY,
  courseid INTEGER DEFAULT NULL,
  title VARCHAR(255) DEFAULT NULL,
  position INTEGER DEFAULT NULL,
  startdate DATE DEFAULT NULL,
  enddate DATE DEFAULT NULL
);

--
-- Dumping data for table "sections"
--

INSERT INTO sections (sectionid, courseid, title, position, startdate, enddate) VALUES
(1, 1, 'ngonngon', 1, NULL, NULL),
(2, 1, 'ngonngon2', 2, NULL, NULL),
(8, 4, 'Java Cơ Bản', 1, NULL, NULL),
(10, 4, 'Java Cơ Bản', 2, NULL, NULL);

-- Reset sequence for sections
SELECT setval('sections_sectionid_seq', (SELECT MAX(sectionid) FROM sections));

-- --------------------------------------------------------

--
-- Table structure for table "submissions"
--

CREATE TABLE submissions (
  submissionid SERIAL PRIMARY KEY,
  assignmentid INTEGER DEFAULT NULL,
  studentid INTEGER DEFAULT NULL,
  fileurl VARCHAR(255) DEFAULT NULL,
  submittedat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  score DOUBLE PRECISION DEFAULT NULL,
  feedback TEXT
);

--
-- Dumping data for table "submissions"
--

INSERT INTO submissions (submissionid, assignmentid, studentid, fileurl, submittedat, score, feedback) VALUES
(1, 1, 2, '/submissions/9dce1408-ac0b-4669-bbe0-778303a8f4e2.docx', '2025-12-22 07:28:47', NULL, NULL);

-- Reset sequence for submissions
SELECT setval('submissions_submissionid_seq', (SELECT MAX(submissionid) FROM submissions));

-- --------------------------------------------------------

--
-- Table structure for table "users"
--

CREATE TABLE users (
  userid SERIAL PRIMARY KEY,
  fullname VARCHAR(100) DEFAULT NULL,
  email VARCHAR(100) UNIQUE DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL,
  role VARCHAR(20) CHECK (role IN ('admin', 'teacher', 'student')) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  avatar VARCHAR(255) DEFAULT NULL,
  status VARCHAR(20) CHECK (status IN ('inactive', 'active')) NOT NULL,
  createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--
-- Dumping data for table "users"
--

INSERT INTO users (userid, fullname, email, password, role, phone, avatar, status, createdat) VALUES
(1, 'phan', 'emaisdasdas', 'dsadsada', 'admin', '03623721231', NULL, 'active', '2025-12-16 06:09:46'),
(2, 'phan', 'ealoalaoas', 'dsadsada', 'student', '03623721231', NULL, 'active', '2025-12-16 06:10:18'),
(3, 'bominh', 'dbrr231104@gmail.com', 'aloaloa', 'teacher', '0239238912', NULL, 'active', '2025-12-16 06:18:51'),
(4, 'Nguyen Van b', 'vanb@gmail.com', '$2a$10$28fFBp7LfP9W5Ee0yEUEkuv3Ebp7OQiIykDFXDydTCh7N1aprnV8G', 'admin', '0123456789', 'avatar.jpg', 'active', '2025-12-16 07:59:24'),
(5, 'Nguyen Van c', 'vanc@gmail.com', '$2a$10$WhQqVjqCzgsUT/1.r2CMd.eiRlT5Q4cwReuOUvDBLK19slXnhwP5O', 'teacher', '0124456789', 'avatar.jpg', 'active', '2025-12-17 02:23:59');

-- Reset sequence for users
SELECT setval('users_userid_seq', (SELECT MAX(userid) FROM users));

--
-- Indexes for dumped tables
--

-- Additional indexes for table "answers"
CREATE INDEX idx_answers_questionid ON answers(questionid);

-- Additional indexes for table "assignments"
CREATE INDEX idx_assignments_courseid ON assignments(courseid);

-- Additional indexes for table "attendance"
CREATE INDEX idx_attendance_courseid ON attendance(course_id);
CREATE INDEX idx_attendance_studentid ON attendance(student_id);

-- Additional indexes for table "chat_messages"
CREATE INDEX idx_chat_messages_senderid ON chat_messages(sender_id);
CREATE INDEX idx_chat_messages_receiverid ON chat_messages(receiver_id);
CREATE INDEX idx_chat_messages_courseid ON chat_messages(course_id);

-- Additional indexes for table "courses"
CREATE INDEX idx_courses_teacherid ON courses(teacherid);

-- Additional indexes for table "enrollments"
CREATE INDEX idx_enrollments_courseid ON enrollments(courseid);
CREATE INDEX idx_enrollments_studentid ON enrollments(studentid);

-- Additional indexes for table "gradebook"
CREATE INDEX idx_gradebook_studentid ON gradebook(student_id);
CREATE INDEX idx_gradebook_courseid ON gradebook(course_id);

-- Additional indexes for table "lessons"
CREATE UNIQUE INDEX idx_lessons_section_position ON lessons(sectionid, position);

-- Additional indexes for table "materials"
CREATE INDEX idx_materials_lessonid ON materials(lessonid);

-- Additional indexes for table "notifications"
CREATE INDEX idx_notifications_userid ON notifications(user_id);

-- Additional indexes for table "questions"
CREATE INDEX idx_questions_quizid ON questions(quizid);

-- Additional indexes for table "quizzes"
CREATE INDEX idx_quizzes_courseid ON quizzes(courseid);

-- Additional indexes for table "quiz_results"
CREATE INDEX idx_quiz_results_quizid ON quiz_results(quiz_id);
CREATE INDEX idx_quiz_results_studentid ON quiz_results(student_id);

-- Additional indexes for table "schedules"
CREATE INDEX idx_schedules_courseid ON schedules(courseid);
CREATE INDEX idx_schedules_sectionid ON schedules(sectionid);
CREATE INDEX idx_schedules_lessonid ON schedules(lessonid);

-- Additional indexes for table "sections"
CREATE UNIQUE INDEX idx_sections_course_position ON sections(courseid, position);

-- Additional indexes for table "submissions"
CREATE INDEX idx_submissions_assignmentid ON submissions(assignmentid);
CREATE INDEX idx_submissions_studentid ON submissions(studentid);

--
-- Constraints for dumped tables
--

-- Constraints for table "answers"
ALTER TABLE answers
  ADD CONSTRAINT fk_answers_questionid FOREIGN KEY (questionid) REFERENCES questions (questionid);

-- Constraints for table "assignments"
ALTER TABLE assignments
  ADD CONSTRAINT fk_assignments_courseid FOREIGN KEY (courseid) REFERENCES courses (courseid);

-- Constraints for table "attendance"
ALTER TABLE attendance
  ADD CONSTRAINT fk_attendance_courseid FOREIGN KEY (course_id) REFERENCES courses (courseid),
  ADD CONSTRAINT fk_attendance_studentid FOREIGN KEY (student_id) REFERENCES users (userid);

-- Constraints for table "chat_messages"
ALTER TABLE chat_messages
  ADD CONSTRAINT fk_chat_messages_senderid FOREIGN KEY (sender_id) REFERENCES users (userid),
  ADD CONSTRAINT fk_chat_messages_receiverid FOREIGN KEY (receiver_id) REFERENCES users (userid),
  ADD CONSTRAINT fk_chat_messages_courseid FOREIGN KEY (course_id) REFERENCES courses (courseid);

-- Constraints for table "courses"
ALTER TABLE courses
  ADD CONSTRAINT fk_courses_teacherid FOREIGN KEY (teacherid) REFERENCES users (userid);

-- Constraints for table "enrollments"
ALTER TABLE enrollments
  ADD CONSTRAINT fk_enrollments_courseid FOREIGN KEY (courseid) REFERENCES courses (courseid),
  ADD CONSTRAINT fk_enrollments_studentid FOREIGN KEY (studentid) REFERENCES users (userid);

-- Constraints for table "gradebook"
ALTER TABLE gradebook
  ADD CONSTRAINT fk_gradebook_studentid FOREIGN KEY (student_id) REFERENCES users (userid),
  ADD CONSTRAINT fk_gradebook_courseid FOREIGN KEY (course_id) REFERENCES courses (courseid);

-- Constraints for table "lessons"
ALTER TABLE lessons
  ADD CONSTRAINT fk_lessons_sectionid FOREIGN KEY (sectionid) REFERENCES sections (sectionid);

-- Constraints for table "materials"
ALTER TABLE materials
  ADD CONSTRAINT fk_materials_lessonid FOREIGN KEY (lessonid) REFERENCES lessons (lessonid);

-- Constraints for table "notifications"
ALTER TABLE notifications
  ADD CONSTRAINT fk_notifications_userid FOREIGN KEY (user_id) REFERENCES users (userid);

-- Constraints for table "questions"
ALTER TABLE questions
  ADD CONSTRAINT fk_questions_quizid FOREIGN KEY (quizid) REFERENCES quizzes (quizid);

-- Constraints for table "quizzes"
ALTER TABLE quizzes
  ADD CONSTRAINT fk_quizzes_courseid FOREIGN KEY (courseid) REFERENCES courses (courseid);

-- Constraints for table "quiz_results"
ALTER TABLE quiz_results
  ADD CONSTRAINT fk_quiz_results_quizid FOREIGN KEY (quiz_id) REFERENCES quizzes (quizid),
  ADD CONSTRAINT fk_quiz_results_studentid FOREIGN KEY (student_id) REFERENCES users (userid);

-- Constraints for table "schedules"
ALTER TABLE schedules
  ADD CONSTRAINT fk_schedules_courseid FOREIGN KEY (courseid) REFERENCES courses (courseid),
  ADD CONSTRAINT fk_schedules_sectionid FOREIGN KEY (sectionid) REFERENCES sections (sectionid),
  ADD CONSTRAINT fk_schedules_lessonid FOREIGN KEY (lessonid) REFERENCES lessons (lessonid);

-- Constraints for table "sections"
ALTER TABLE sections
  ADD CONSTRAINT fk_sections_courseid FOREIGN KEY (courseid) REFERENCES courses (courseid);

-- Constraints for table "submissions"
ALTER TABLE submissions
  ADD CONSTRAINT fk_submissions_assignmentid FOREIGN KEY (assignmentid) REFERENCES assignments (assignmentid),
  ADD CONSTRAINT fk_submissions_studentid FOREIGN KEY (studentid) REFERENCES users (userid);
