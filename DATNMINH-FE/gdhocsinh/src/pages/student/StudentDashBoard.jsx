import React, { useEffect, useState } from "react";
import "../../assets/CSS/StudentDashBoard.css";
import { Icon } from "@iconify/react";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import GetCourseComponent from "../../components/GetCourseComponent.jsx";
import GetMyCourseComponent from "../../components/GetMyCourseComponent.jsx";
import { getCourses, getMyCourses } from "../../api/Courseapi.jsx";

function StudentDashBoard() {
  const [allCourses, setCourses] = useState([]);
  const [myCourses, setMyCourses] = useState([]);
  const [loading, setLoading] = useState(true);

  const token = sessionStorage.getItem("token");
  const userStr = sessionStorage.getItem("user");
  const user = userStr ? JSON.parse(userStr) : null;
  const studentId = user ? user.id : null;

  useEffect(() => {
    if (!token || !studentId) return;
    setLoading(true);
    Promise.all([
      getCourses(token),
      getMyCourses(studentId, token)
    ])
      .then(([all, mine]) => {
        setCourses(all);
        setMyCourses(mine);
      })
      .finally(() => setLoading(false));
  }, [token, studentId]);

  // Lọc ra các khóa học chưa tham gia
  const myCourseIds = new Set(myCourses.map(c => c.id));
  const availableCourses = allCourses.filter(c => !myCourseIds.has(c.id));

  return (
    <div className="dashboard-root" style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <main className="container" style={{ padding: "32px", height: "100%" }}>
          {/* Banner ... */}
<div className="banner-section" style={{
            background: "#eaf3ff", borderRadius: 16, padding: 32, display: "flex", alignItems: "center", marginBottom: 32
          }}>
            <img
              src="./picture/banner.jpg"
              alt="Students"
              className="banner-img"
              style={{ width: 120, height: 120, borderRadius: 16, marginRight: 32, objectFit: "cover" }}
            />
            <div className="banner-content">
              <h1 className="banner-title" style={{ fontSize: 24, fontWeight: 700, marginBottom: 8 }}>Chương Trình Chất Lượng Cao</h1>
              <p style={{ marginBottom: 24, fontSize: 16, opacity: 0.9 }}>
                Nâng cao kiến thức và kỹ năng với các khóa học hàng đầu.
              </p>
              <button className="banner-btn" data-media-type="banani-button" style={{
                background: "#007bff", color: "#fff", border: "none", borderRadius: 8, padding: "8px 20px", fontWeight: 600, cursor: "pointer"
              }}>
                Thông tin đào tạo
                <Icon icon="lucide:arrow-right" style={{ fontSize: 16, marginLeft: 8 }} />
              </button>
            </div>
          </div>
          {/* Course Categories */}
          <div className="section-header" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 8 }}>
            <div className="section-title" style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
              <Icon icon="lucide:layers" style={{ color: 'var(--primary, #007bff)', fontSize: 24 }} />
              Danh mục khóa học
            </div>
            <div className="view-all" style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Xem tất cả</div>
          </div>
          {/* Truyền availableCourses vào component */}
          <GetCourseComponent courses={availableCourses} loading={loading} userId={studentId}/>

          {/* My Courses */}
          <div className="section-header" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", margin: "32px 0 8px 0" }}>
            <div className="section-title" style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
              <Icon icon="lucide:graduation-cap" style={{ color: 'var(--accent-red, #f44336)', fontSize: 24 }} />
              Khóa học của tôi
            </div>
            <div className="view-all" style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Xem tất cả</div>
          </div>
          <GetMyCourseComponent courses={myCourses} loading={loading} />
        </main>
        <footer className="footer" style={{ textAlign: "center", color: "#888", fontSize: 14, margin: "32px 0 0 0" }}>
          © 2025 Học Viện Ngân Hàng. All rights reserved.<br />
          Hệ thống hỗ trợ học tập trực tuyến.
        </footer>
      </div>
    </div>
  );
}

export default StudentDashBoard;