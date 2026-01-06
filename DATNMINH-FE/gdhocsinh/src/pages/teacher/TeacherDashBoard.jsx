import React, { useEffect, useState } from "react";
import "../../assets/CSS/StudentDashBoard.css";
import { Icon } from "@iconify/react";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import GetMyTeacherCourseComponent from "../../components/GetMyTeacherCourseComponent.jsx";
import { getMyCourses } from "../../api/Courseapi.jsx";
// import CreateAssignmentForm from "../../components/CreateAssignmentForm"; // Nếu có

function TeacherDashBoard() {
  const [myCourses, setMyCourses] = useState([]);
  const [loading, setLoading] = useState(true);

  const token = sessionStorage.getItem("token");
  const userStr = sessionStorage.getItem("user");
  const user = userStr ? JSON.parse(userStr) : null;
  const teacherId = user ? user.id : null;

  useEffect(() => {
    if (!token || !teacherId) return;
    setLoading(true);
    getMyCourses(teacherId, token)
      .then(setMyCourses)
      .finally(() => setLoading(false));
  }, [token, teacherId]);

  return (
    <div className="dashboard-root" style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <main className="container" style={{ padding: "32px", height: "100%" }}>
          {/* Banner */}
          <div className="banner-section" style={{
            background: "#eaf3ff", borderRadius: 16, padding: 32, display: "flex", alignItems: "center", marginBottom: 32
          }}>
            <img
              src="./picture/banner.jpg"
              alt="Teacher"
              className="banner-img"
              style={{ width: 120, height: 120, borderRadius: 16, marginRight: 32, objectFit: "cover" }}
            />
            <div className="banner-content">
              <h1 className="banner-title" style={{ fontSize: 24, fontWeight: 700, marginBottom: 8 }}>Chào mừng giáo viên!</h1>
              <p style={{ marginBottom: 24, fontSize: 16, opacity: 0.9 }}>
                Quản lý các khóa học, tạo bài tập và kiểm tra sinh viên dễ dàng.
              </p>
            </div>
          </div>

          {/* My Courses */}
          <div className="section-header" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", margin: "32px 0 8px 0" }}>
            <div className="section-title" style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
              <Icon icon="lucide:graduation-cap" style={{ color: 'var(--accent-red, #f44336)', fontSize: 24 }} />
              Khóa học tôi phụ trách
            </div>
            <div className="view-all" style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Xem tất cả</div>
          </div>
          <GetMyTeacherCourseComponent courses={myCourses} loading={loading} />

          {/* Nếu muốn: Thêm nút tạo assignment/quiz ở đây */}
          {/* <CreateAssignmentForm ... /> */}

        </main>
        <footer className="footer" style={{ textAlign: "center", color: "#888", fontSize: 14, margin: "32px 0 0 0" }}>
          © 2025 Học Viện Ngân Hàng. All rights reserved.<br />
          Hệ thống hỗ trợ học tập trực tuyến.
        </footer>
      </div>
    </div>
  );
}

export default TeacherDashBoard;