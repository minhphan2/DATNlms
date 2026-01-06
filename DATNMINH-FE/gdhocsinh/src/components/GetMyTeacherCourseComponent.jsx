import React from "react";
import { useNavigate } from "react-router-dom";
import "../assets/CSS/StudentDashBoard.css";

function GetMyTeacherCourseComponent({ courses, loading }) {
  const navigate = useNavigate();

  if (loading) return <div>Đang tải danh sách khóa học...</div>;
  if (!courses || courses.length === 0) {
    return <div>Bạn chưa phụ trách khóa học nào.</div>;
  }

  return (
    <div className="courses-grid">
      {courses.map(course => (
        <div
          key={course.id}
          className="course-card"
          style={{ cursor: "pointer" }}
          onClick={() => navigate(`/teacher/course/${course.id}`)}
        >
          <span className="course-code">{course.courseName}</span>
          <h3 className="course-title">{course.title}</h3>
          <p className="course-meta">{course.description}</p>
          <div style={{ fontSize: 13, color: "#888" }}>
            <b>Thể loại:</b> {course.category} <br />
            <b>Trạng thái:</b> {course.status}
          </div>
        </div>
      ))}
    </div>
  );
}

export default GetMyTeacherCourseComponent;