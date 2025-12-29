import React, { useEffect } from "react";
import '../assets/CSS/StudentDashBoard.css';
import {Icon} from "@iconify/react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getProfile } from "../api/Profileapi.jsx";
import GetCourseComponent from "../components/GetCourseComponent.jsx";
import GetMyCourseComponent from "../components/GetMyCourseComponent.jsx";
function StudentDashBoard() {

  

  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  }



  const [user, setUser] = useState(null);

  useEffect(() => {
     const token = localStorage.getItem('token');
    getProfile(token)
      .then(data => setUser(data))
      .catch(err => console.error(err));
  }, []);
  if (!user) {
    return <div>Loading...</div>;
  }
  return (
    <div id="dashboard-page">
      {/* Top Bar */}
      <div className="top-nav">
        <div className="nav-links">
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:home" style={{ fontSize: 14 }}></Icon>
            Home
          </div>
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:layout-dashboard" style={{ fontSize: 14 }}></Icon>
            Dashboard
          </div>
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:book-open" style={{ fontSize: 14 }}></Icon>
            My courses
          </div>
        </div>
        <div className="nav-item" data-media-type="banani-button">
          ENGLISH (EN)
          <Icon icon="lucide:chevron-down" style={{ fontSize: 14 }}></Icon>
        </div>
      </div>

      {/* Main Brand Header */}
      <header className="main-header">
        <div className="brand-container">
          <img
            src="/picture/logotron.jpg"
            alt="Logo"
            className="logo"
          />
          <div className="brand-text">
            <span className="brand-title">Học Viện Ngân Hàng</span>
            <span className="brand-subtitle">Cổng Học Tập Trực Tuyến</span>
          </div>
        </div>
         <div className="header-right" data-media-type="banani-button">
          <div className="user-info">
            <span className="user-name">{user.fullname}</span>
            <span className="user-role">{user.role}</span>
            <span className="user-id">{user.id}</span>
          </div>
          <button onClick={handleLogout}> Đăng xuất</button>
          <img
            src="https://app.banani.co/avatar1.jpeg"
            alt="User avatar"
            className="user-avatar"
          />
        </div>
      </header>

      <main className="container">
        {/* Banner */}
        <div className="banner-section">
          <img
            src="./picture/banner.jpg"
            alt="Students"
            className="banner-img"
          />
          <div className="banner-content">
            <h1 className="banner-title">Chương Trình Chất Lượng Cao</h1>
            <p style={{ marginBottom: 24, fontSize: 16, opacity: 0.9 }}>
              Nâng cao kiến thức và kỹ năng với các khóa học hàng đầu.
            </p>
            <button className="banner-btn" data-media-type="banani-button">
              Thông tin đào tạo
              <Icon icon="lucide:arrow-right" style={{ fontSize: 16 }}></Icon>
            </button>
          </div>
        </div>

        {/* Course Categories */}
        <div className="section-header">
          <div className="section-title">
            <Icon icon="lucide:layers" style={{ color: 'var(--primary)', fontSize: 24 }}></Icon>
            Danh mục khóa học
          </div>
          <div className="view-all" data-media-type="banani-button">Xem tất cả</div>
        </div>

        
          {/* Các category-card */}
          {/* ...giữ nguyên các category-card, đổi class thành className... */}
          {<GetCourseComponent/>}
        

        {/* My Courses */}
        <div className="section-header">
          <div className="section-title">
            <Icon icon="lucide:graduation-cap" style={{ color: 'var(--accent-red)', fontSize: 24 }}></Icon>
            Khóa học của tôi
          </div>
          <div className="view-all" data-media-type="banani-button">Xem tất cả</div>
        </div>

          {/* Các course-card */}
          {/* ...giữ nguyên các course-card, đổi class thành className... */}
          {<GetMyCourseComponent/>}
      </main>

      <footer className="footer">
        © 2025 Học Viện Ngân Hàng. All rights reserved.<br />
        Hệ thống hỗ trợ học tập trực tuyến.
      </footer>

      <div className="help-btn" data-media-type="banani-button">
        <Icon icon="lucide:message-circle-question" style={{ fontSize: 28 }}></Icon>
      </div>
    </div>
  );
}

export default StudentDashBoard;