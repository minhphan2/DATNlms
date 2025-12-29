import React from "react";
import { Icon } from "@iconify/react";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getProfile } from "../api/Profileapi.jsx";

const Headder = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    getProfile(token)
      .then(data => setUser(data))
      .catch(() => setUser(null));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  };

  if (!user) return null;

  return (
    <>
      {/* Top Bar */}
      <div className="top-nav">
        <div className="nav-links">
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:home" style={{ fontSize: 14 }} />
            Home
          </div>
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:layout-dashboard" style={{ fontSize: 14 }} />
            Dashboard
          </div>
          <div className="nav-item" data-media-type="banani-button">
            <Icon icon="lucide:book-open" style={{ fontSize: 14 }} />
            My courses
          </div>
        </div>
        <div className="nav-item" data-media-type="banani-button">
          ENGLISH (EN)
          <Icon icon="lucide:chevron-down" style={{ fontSize: 14 }} />
        </div>
      </div>

      {/* Main Brand Header */}
      <header className="main-header">
        <div className="brand-container">
          <img src="/picture/logotron.jpg" alt="Logo" className="logo" />
          <div className="brand-text">
            <span className="brand-title">Học Viện Ngân Hàng</span>
            <span className="brand-subtitle">Cổng Học Tập Trực Tuyến</span>
          </div>
        </div>
        <div className="header-right" data-media-type="banani-button">
          <div className="user-info">
            <span className="user-name">{user?.fullname}</span>
            <span className="user-role">{user?.role}</span>
            <span className="user-id">{user?.id}</span>
          </div>
          <button onClick={handleLogout}>Đăng xuất</button>
          <img
            src="https://app.banani.co/avatar1.jpeg"
            alt="User avatar"
            className="user-avatar"
          />
        </div>
      </header>
    </>
  );
};

export default Headder;