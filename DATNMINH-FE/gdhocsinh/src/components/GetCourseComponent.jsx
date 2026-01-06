import React from "react";
import { useNavigate } from "react-router-dom";
import '../assets/CSS/StudentDashBoard.css';
import { enrollCourse } from "../api/Courseapi"; // Bạn cần tạo hàm này

function GetCourseComponent({ courses, loading, userId, onEnrollSuccess }) {
  const navigate = useNavigate();

  const handleEnroll = async (courseId) => {
    try {
      await enrollCourse(userId, courseId, sessionStorage.getItem('token'));
      if (onEnrollSuccess) onEnrollSuccess(); // reload lại dashboard
      alert("Đăng ký thành công!");
    } catch (e) {
      alert("Đăng ký thất bại!");
    }
  };

  if (loading) return <div>Đang tải danh sách khóa học...</div>;
  if (!courses || courses.length === 0) {
    return <div>Không còn khóa học nào bạn chưa tham gia.</div>;
  }
  return (
    <div className="courses-grid">
      {courses.map(course => (
        <div
          key={course.id}
          className="course-card"
          style={{ cursor: "pointer", position: "relative" }}
          onClick={() => navigate(`/courses/${course.id}`)}
        >
          <span className="course-code">{course.id}</span>
          <h3 className="course-title">{course.title}</h3>
          <p className="course-meta">{course.description}</p>
          <p
            className="enroll-button"
            style={{bottom: 16, right: 16, color: "#007bff", cursor: "pointer" }}
            onClick={e => {
              e.stopPropagation(); // Không trigger navigate
              handleEnroll(course.courseId || course.id);
            }}
          >
            Đăng ký
          </p>
        </div>
      ))}
    </div>
  );
}

export default GetCourseComponent;