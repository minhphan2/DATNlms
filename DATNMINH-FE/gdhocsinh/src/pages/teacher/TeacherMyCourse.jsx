import React, { useEffect, useState } from "react";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import { Icon } from "@iconify/react";
import { useNavigate } from "react-router-dom";
import { getCourses } from "../../api/Courseapi";

function MyCourse() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Lấy thông tin user từ sessionStorage
  const userStr = sessionStorage.getItem("user");
  const user = userStr ? JSON.parse(userStr) : null;
  const teacherName = user?.fullname;

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    getCourses(token)
      .then(data => {
        // Lọc các course mà teacherName trùng với tên giáo viên đăng nhập
        const myCourses = data.filter(course => course.teacherName === teacherName);
        setCourses(myCourses);
      })
      .finally(() => setLoading(false));
  }, [teacherName]);

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ padding: "32px" }}>
          <h1 style={{ fontSize: 28, fontWeight: 700, marginBottom: 24 }}>
            <Icon icon="lucide:book-open" style={{ fontSize: 32, color: "#007bff", marginRight: 12 }} />
            Khóa học tôi phụ trách
          </h1>
          {loading ? (
            <div>Đang tải...</div>
          ) : courses.length === 0 ? (
            <div>Bạn chưa phụ trách khóa học nào.</div>
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
                  <div style={{ color: "#888", fontSize: 15 }}>{course.courseName}</div>
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
                    onClick={() => navigate(`/teacher/course/${course.id}`)}
                  >
                    Quản lý khóa học
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