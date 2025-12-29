import React from "react";
import "../assets/CSS/StudentDashBoard.css";
import { Icon } from "@iconify/react";
import Headder from "../components/Headder.jsx";
import GetCourseComponent from "../components/GetCourseComponent.jsx";
import GetMyCourseComponent from "../components/GetMyCourseComponent.jsx";
import SidebarComponent from "../components/SidebarComponent.jsx";

function StudentDashBoard() {
  return (
    <div className="dashboard-root" style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
     <SidebarComponent/>
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        {/* Header */}

        {/* Main dashboard content */}
        <main className="container" style={{ padding: "32px", height: "100%" }}>
          {/* Banner */}
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
            <div className="view-all" data-media-type="banani-button" style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Xem tất cả</div>
          </div>
          <GetCourseComponent />

          {/* My Courses */}
          <div className="section-header" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", margin: "32px 0 8px 0" }}>
            <div className="section-title" style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
              <Icon icon="lucide:graduation-cap" style={{ color: 'var(--accent-red, #f44336)', fontSize: 24 }} />
              Khóa học của tôi
            </div>
            <div className="view-all" data-media-type="banani-button" style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Xem tất cả</div>
          </div>
          <GetMyCourseComponent />
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