import React from "react";
import '../assets/CSS/StudentDashBoard.css';
import { useNavigate } from "react-router-dom";

function GetMyCourseComponent({ courses, loading }) {
    const navigate = useNavigate();
  if (loading) return <div>Đang tải danh sách khóa học của bạn...</div>;
  if (!courses || courses.length === 0) {
    return <div>Bạn chưa tham gia khóa học nào.</div>;
  }
  return (
    <div className="courses-grid">
      {courses.map(course => (
        <div key={course.id} className="course-card"
        style={{cursor: "pointer"}}
        onClick={() => navigate(`/courses/${course.id}`)}
        >
          <span className="course-code">{course.id}</span>
          <h3 className="course-title">{course.title}</h3>
          <p className="course-meta">{course.description}</p>
        </div>
      ))}
    </div>
  );
}

export default GetMyCourseComponent;