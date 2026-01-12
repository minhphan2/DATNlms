-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2025 at 06:48 AM
-- Server version: 8.0.44
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lms`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `answerid` int NOT NULL,
  `questionid` int DEFAULT NULL,
  `answertext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `iscorrect` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assignments`
--

CREATE TABLE `assignments` (
  `assignmentid` int NOT NULL,
  `courseid` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `instructions` text,
  `deadline` datetime DEFAULT NULL,
  `maxscore` int DEFAULT '100',
  `createdat` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `attachmenturl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `assignments`
--

INSERT INTO `assignments` (`assignmentid`, `courseid`, `title`, `instructions`, `deadline`, `maxscore`, `createdat`, `attachmenturl`) VALUES
(1, 1, 'Bài tập 1: Java OOP', NULL, '2025-12-25 23:59:59', 100, '2025-12-18 09:47:02', '/assignments/3aca7887-e19d-4c18-9e89-dd64865e1e4b.pdf'),
(2, 1, 'Bài tập 1: Java OOP', 'Làm bài tập về Kế thừa và Đa hình', '2025-12-25 23:59:59', 100, '2025-12-18 09:47:12', '/assignments/771bdae0-7b3a-45be-b5e3-582338370e0c.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int NOT NULL,
  `course_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` enum('present','absent','late') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chat_messages`
--

CREATE TABLE `chat_messages` (
  `message_id` int NOT NULL,
  `sender_id` int DEFAULT NULL,
  `receiver_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `message` text,
  `sent_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseid` int NOT NULL,
  `teacherid` int DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `thumbnail` varchar(300) DEFAULT NULL,
  `status` enum('active','inactive') DEFAULT 'active',
  `createdat` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `coursename` varchar(200) NOT NULL,
  `maxstudents` int DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseid`, `teacherid`, `title`, `description`, `category`, `thumbnail`, `status`, `createdat`, `coursename`, `maxstudents`, `startdate`, `enddate`) VALUES
(1, 5, 'Java Spring Boot Nâng Cao', 'Khóa học nâng cao cho developer', 'Programming', 'thumbnail-advanced.jpg', 'active', '2025-12-16 09:18:44', 'JAVA102', NULL, NULL, NULL),
(2, 3, 'Java Spring Boot Căn Bản', 'Khóa học Java Spring Boot từ đầu cho người mới bắt đầu', 'Programming', 'https://example.com/java-course.jpg', 'active', '2025-12-17 02:39:33', 'java', NULL, NULL, NULL),
(3, 3, 'Java Spring Boot Căn Bản2', 'Khóa học Java Spring Boot từ đầu cho người mới bắt đầu', 'Programming', 'https://example.com/java-course.jpg', 'active', '2025-12-17 03:09:18', 'java', NULL, NULL, NULL),
(4, 3, 'Java Spring Boot Căn Bản', 'Khóa học Java cho người mới', 'Programming', 'java.jpg', 'active', '2025-12-17 06:09:39', 'JAVA101', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `enrollments`
--

CREATE TABLE `enrollments` (
  `enrollmentid` int NOT NULL,
  `courseid` int DEFAULT NULL,
  `studentid` int DEFAULT NULL,
  `enrolledat` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `enrollments`
--

INSERT INTO `enrollments` (`enrollmentid`, `courseid`, `studentid`, `enrolledat`) VALUES
(1, 1, 3, '2025-12-17 06:16:02'),
(2, 1, 3, '2025-12-17 06:16:07'),
(3, 4, 3, '2025-12-17 06:16:43'),
(4, 4, 3, '2025-12-17 06:16:45'),
(5, 4, 5, '2025-12-17 06:16:53'),
(6, 4, 2, '2025-12-17 06:42:17');

-- --------------------------------------------------------

--
-- Table structure for table `gradebook`
--

CREATE TABLE `gradebook` (
  `grade_id` int NOT NULL,
  `student_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `assignment_score` float DEFAULT '0',
  `quiz_score` float DEFAULT '0',
  `final_score` float DEFAULT '0',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lessons`
--

CREATE TABLE `lessons` (
  `lessonid` int NOT NULL,
  `sectionid` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `videourl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `position` int DEFAULT NULL,
  `createdat` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lessons`
--

INSERT INTO `lessons` (`lessonid`, `sectionid`, `title`, `content`, `videourl`, `position`, `createdat`) VALUES
(1, 1, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 1, '2025-12-18 02:32:30'),
(3, 1, 'Bài 1: Giới thiệu Java', 'Java là ngôn ngữ lập trình hướng đối tượng...', 'https://youtube.com/watch?v=abc123', 2, '2025-12-18 02:35:53'),
(5, 1, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 3, '2025-12-18 02:36:12'),
(6, 10, 'Bài 1: Giới thiệu Java', NULL, 'https://youtube.com/watch?v=abc123', 3, '2025-12-18 02:36:39');

-- --------------------------------------------------------

--
-- Table structure for table `materials`
--

CREATE TABLE `materials` (
  `materialid` int NOT NULL,
  `lessonid` int DEFAULT NULL,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fileurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `uploadedat` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `filetype` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `materials`
--

INSERT INTO `materials` (`materialid`, `lessonid`, `filename`, `fileurl`, `uploadedat`, `filetype`) VALUES
(1, 1, 'Phan Tuấn Minh_CV.pdf.pdf', '/materials/ac8d1fe9-2e4f-4379-9e86-3b655024120c.pdf', '2025-12-18 04:34:16', 'application/pdf');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `notification_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `message` text,
  `is_read` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `questionid` int NOT NULL,
  `quizid` int DEFAULT NULL,
  `questiontext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `type` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quizzes`
--

CREATE TABLE `quizzes` (
  `quizid` int NOT NULL,
  `courseid` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `timelimit` int DEFAULT NULL,
  `createdat` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_results`
--

CREATE TABLE `quiz_results` (
  `result_id` int NOT NULL,
  `quiz_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `taken_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `scheduleid` int NOT NULL,
  `courseid` int DEFAULT NULL,
  `sectionid` int DEFAULT NULL,
  `lessonid` int DEFAULT NULL,
  `dayofweek` tinyint NOT NULL,
  `starttime` varchar(255) NOT NULL,
  `endtime` varchar(255) NOT NULL,
  `startdate` datetime(6) NOT NULL,
  `enddate` datetime(6) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `schedules`
--

INSERT INTO `schedules` (`scheduleid`, `courseid`, `sectionid`, `lessonid`, `dayofweek`, `starttime`, `endtime`, `startdate`, `enddate`, `location`, `type`) VALUES
(1, 2, 1, 1, 1, '09:00', '11:00', '2025-12-23 09:00:00.000000', '2025-12-23 11:00:00.000000', 'Phòng 102', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sections`
--

CREATE TABLE `sections` (
  `sectionid` int NOT NULL,
  `courseid` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `position` int DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sections`
--

INSERT INTO `sections` (`sectionid`, `courseid`, `title`, `position`, `startdate`, `enddate`) VALUES
(1, 1, 'ngonngon', 1, NULL, NULL),
(2, 1, 'ngonngon2', 2, NULL, NULL),
(8, 4, 'Java Cơ Bản', 1, NULL, NULL),
(10, 4, 'Java Cơ Bản', 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `submissions`
--

CREATE TABLE `submissions` (
  `submissionid` int NOT NULL,
  `assignmentid` int DEFAULT NULL,
  `studentid` int DEFAULT NULL,
  `fileurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `submittedat` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `score` double DEFAULT NULL,
  `feedback` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `submissions`
--

INSERT INTO `submissions` (`submissionid`, `assignmentid`, `studentid`, `fileurl`, `submittedat`, `score`, `feedback`) VALUES
(1, 1, 2, '/submissions/9dce1408-ac0b-4669-bbe0-778303a8f4e2.docx', '2025-12-22 07:28:47', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int NOT NULL,
  `fullname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('admin','teacher','student') NOT NULL,
  `phone` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `status` enum('inactive','active') NOT NULL,
  `createdat` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `fullname`, `email`, `password`, `role`, `phone`, `avatar`, `status`, `createdat`) VALUES
(1, 'phan', 'emaisdasdas', 'dsadsada', 'admin', '03623721231', NULL, 'active', '2025-12-16 06:09:46'),
(2, 'phan', 'ealoalaoas', 'dsadsada', 'student', '03623721231', NULL, 'active', '2025-12-16 06:10:18'),
(3, 'bominh', 'dbrr231104@gmail.com', 'aloaloa', 'teacher', '0239238912', NULL, 'active', '2025-12-16 06:18:51'),
(4, 'Nguyen Van b', 'vanb@gmail.com', '$2a$10$28fFBp7LfP9W5Ee0yEUEkuv3Ebp7OQiIykDFXDydTCh7N1aprnV8G', 'admin', '0123456789', 'avatar.jpg', 'active', '2025-12-16 07:59:24'),
(5, 'Nguyen Van c', 'vanc@gmail.com', '$2a$10$WhQqVjqCzgsUT/1.r2CMd.eiRlT5Q4cwReuOUvDBLK19slXnhwP5O', 'teacher', '0124456789', 'avatar.jpg', 'active', '2025-12-17 02:23:59');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`answerid`),
  ADD KEY `question_id` (`questionid`);

--
-- Indexes for table `assignments`
--
ALTER TABLE `assignments`
  ADD PRIMARY KEY (`assignmentid`),
  ADD KEY `course_id` (`courseid`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseid`),
  ADD KEY `teacher_id` (`teacherid`);

--
-- Indexes for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD PRIMARY KEY (`enrollmentid`),
  ADD KEY `course_id` (`courseid`),
  ADD KEY `student_id` (`studentid`);

--
-- Indexes for table `gradebook`
--
ALTER TABLE `gradebook`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `lessons`
--
ALTER TABLE `lessons`
  ADD PRIMARY KEY (`lessonid`),
  ADD UNIQUE KEY `unique_section_position` (`sectionid`,`position`),
  ADD UNIQUE KEY `unique_sections_position` (`sectionid`,`position`);

--
-- Indexes for table `materials`
--
ALTER TABLE `materials`
  ADD PRIMARY KEY (`materialid`),
  ADD KEY `lesson_id` (`lessonid`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`questionid`),
  ADD KEY `quiz_id` (`quizid`);

--
-- Indexes for table `quizzes`
--
ALTER TABLE `quizzes`
  ADD PRIMARY KEY (`quizid`),
  ADD KEY `course_id` (`courseid`);

--
-- Indexes for table `quiz_results`
--
ALTER TABLE `quiz_results`
  ADD PRIMARY KEY (`result_id`),
  ADD KEY `quiz_id` (`quiz_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`scheduleid`),
  ADD KEY `courseid` (`courseid`),
  ADD KEY `sectionid` (`sectionid`),
  ADD KEY `lessonid` (`lessonid`);

--
-- Indexes for table `sections`
--
ALTER TABLE `sections`
  ADD PRIMARY KEY (`sectionid`),
  ADD UNIQUE KEY `unique_course_position` (`courseid`,`position`);

--
-- Indexes for table `submissions`
--
ALTER TABLE `submissions`
  ADD PRIMARY KEY (`submissionid`),
  ADD KEY `assignment_id` (`assignmentid`),
  ADD KEY `student_id` (`studentid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `answerid` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `assignments`
--
ALTER TABLE `assignments`
  MODIFY `assignmentid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `chat_messages`
--
ALTER TABLE `chat_messages`
  MODIFY `message_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `courseid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `enrollments`
--
ALTER TABLE `enrollments`
  MODIFY `enrollmentid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `gradebook`
--
ALTER TABLE `gradebook`
  MODIFY `grade_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lessons`
--
ALTER TABLE `lessons`
  MODIFY `lessonid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `materials`
--
ALTER TABLE `materials`
  MODIFY `materialid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `notification_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `questionid` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quizzes`
--
ALTER TABLE `quizzes`
  MODIFY `quizid` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quiz_results`
--
ALTER TABLE `quiz_results`
  MODIFY `result_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `scheduleid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sections`
--
ALTER TABLE `sections`
  MODIFY `sectionid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `submissions`
--
ALTER TABLE `submissions`
  MODIFY `submissionid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `questions` (`questionid`);

--
-- Constraints for table `assignments`
--
ALTER TABLE `assignments`
  ADD CONSTRAINT `assignments_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`);

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`courseid`),
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`userid`);

--
-- Constraints for table `chat_messages`
--
ALTER TABLE `chat_messages`
  ADD CONSTRAINT `chat_messages_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`userid`),
  ADD CONSTRAINT `chat_messages_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`userid`),
  ADD CONSTRAINT `chat_messages_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `courses` (`courseid`);

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`teacherid`) REFERENCES `users` (`userid`);

--
-- Constraints for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`),
  ADD CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`studentid`) REFERENCES `users` (`userid`);

--
-- Constraints for table `gradebook`
--
ALTER TABLE `gradebook`
  ADD CONSTRAINT `gradebook_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`userid`),
  ADD CONSTRAINT `gradebook_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`courseid`);

--
-- Constraints for table `lessons`
--
ALTER TABLE `lessons`
  ADD CONSTRAINT `lessons_ibfk_1` FOREIGN KEY (`sectionid`) REFERENCES `sections` (`sectionid`);

--
-- Constraints for table `materials`
--
ALTER TABLE `materials`
  ADD CONSTRAINT `materials_ibfk_1` FOREIGN KEY (`lessonid`) REFERENCES `lessons` (`lessonid`);

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quizid`) REFERENCES `quizzes` (`quizid`);

--
-- Constraints for table `quizzes`
--
ALTER TABLE `quizzes`
  ADD CONSTRAINT `quizzes_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`);

--
-- Constraints for table `quiz_results`
--
ALTER TABLE `quiz_results`
  ADD CONSTRAINT `quiz_results_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quizid`),
  ADD CONSTRAINT `quiz_results_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`userid`);

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`),
  ADD CONSTRAINT `schedules_ibfk_2` FOREIGN KEY (`sectionid`) REFERENCES `sections` (`sectionid`),
  ADD CONSTRAINT `schedules_ibfk_3` FOREIGN KEY (`lessonid`) REFERENCES `lessons` (`lessonid`);

--
-- Constraints for table `sections`
--
ALTER TABLE `sections`
  ADD CONSTRAINT `sections_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`);

--
-- Constraints for table `submissions`
--
ALTER TABLE `submissions`
  ADD CONSTRAINT `submissions_ibfk_1` FOREIGN KEY (`assignmentid`) REFERENCES `assignments` (`assignmentid`),
  ADD CONSTRAINT `submissions_ibfk_2` FOREIGN KEY (`studentid`) REFERENCES `users` (`userid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
