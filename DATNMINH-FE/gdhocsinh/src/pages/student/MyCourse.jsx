import React, { useEffect, useState } from "react";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import Headder from "../../components/Headder.jsx";
import { Icon } from "@iconify/react";
import { useNavigate } from "react-router-dom";

// Giả sử bạn có hàm này để lấy danh sách khóa học
import { getMyCourses } from "../../api/Courseapi.jsx";

function MyCourse() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    getMyCourses(token)
      .then(data => setCourses(data))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ padding: "32px" }}>
          <h1 style={{ fontSize: 28, fontWeight: 700, marginBottom: 24 }}>
            <Icon icon="lucide:book-open" style={{ fontSize: 32, color: "#007bff", marginRight: 12 }} />
            Khóa học của tôi
          </h1>
          {loading ? (
            <div>Đang tải...</div>
          ) : courses.length === 0 ? (
            <div>Bạn chưa đăng ký khóa học nào.</div>
          ) : (
            <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(320px, 1fr))", gap: 24 }}>
              {courses.map(course => (
                <div key={course.id} style={{
                  background: "#fff",
                  borderRadius: 12,
                  boxShadow: "0 2px 8px #eee",
                  padding: 24,
                  display: "flex",
                  flexDirection: "column",
                  gap: 12
                }}>
                  <div style={{ fontWeight: 700, fontSize: 20, color: "#007bff" }}>{course.title}</div>
                  <div style={{ color: "#888", fontSize: 15 }}>{course.code}</div>
                  <div style={{ fontSize: 16 }}>{course.description}</div>
                  <div style={{ marginTop: 8 }}> Tên giáo viên: 
                    <span style={{
                      background: "#eaf3ff",
                      color: "#007bff",
                      borderRadius: 6,
                      padding: "2px 10px",
                      fontSize: 13,
                      fontWeight: 500
                    }}>
                      {course.teacherName}
                    </span>
                  </div>
                  <button
                    style={{
                      marginTop: 12,
                      background: "#007bff",
                      color: "#fff",
                      border: "none",
                      borderRadius: 8,
                      padding: "8px 20px",
                      fontWeight: 600,
                      cursor: "pointer"
                    }}
                    onClick={() => navigate(`/courses/${course.id}`)}
                  >
                    Vào học
                  </button>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default MyCourse;