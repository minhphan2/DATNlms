import React, { useEffect, useState } from "react";
import { Icon } from "@iconify/react";
import { useNavigate, useLocation } from "react-router-dom";
import { getProfile } from "../api/Profileapi.jsx";

const SidebarComponent = () => {
  const [user, setUser] = useState(null);
  const [collapsed, setCollapsed] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const token = sessionStorage.getItem('token');
    getProfile(token)
      .then(data => setUser(data))
      .catch(() => setUser(null));
  }, []);

  const handleLogout = () => {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    navigate('/login');
  };

  // Menu cho từng role
  const menusStudent = [
    { label: "Dashboard", icon: "lucide:layout-dashboard", path: "/student/dashboard" },
    { label: "Khóa Học Của Tôi", icon: "lucide:book-open", path: "/my-courses" },
    { label: "Thời Khóa Biểu", icon: "lucide:calendar", path: "/schedule" },
    { label: "Đăng Ký Khóa Học", icon: "lucide:plus-circle", path: "/register-course" },
  ];
  const menusTeacher = [
    { label: "Dashboard", icon: "lucide:layout-dashboard", path: "/teacher/dashboard" },
    { label: "Khóa học phụ trách", icon: "lucide:book-open", path: "/teacher/my-courses" },
    { label: "Quản lý bài tập", icon: "lucide:clipboard-list", path: "/teacher/assignments" },
  ];
  const menusAdmin = [
    { label: "Quản lý khoa", icon: "lucide:layout-dashboard", path: "/admin/departments" },
    { label: "Quản lý người dùng", icon: "lucide:users", path: "/admin/users" },
    { label: "Quản lý khóa học", icon: "lucide:book", path: "/admin/courses" },
  ];
  const systemMenus = [
    { label: "Documents", icon: "lucide:file-text", path: "/documents" },
    { label: "Settings", icon: "lucide:settings", path: "/settings" },
  ];

  if (!user) return null;

  // Xác định role
  const role = user?.role?.toLowerCase();
  let menus = menusStudent;
  if (role === "teacher") menus = menusTeacher;
  if (role === "admin") menus = menusAdmin;

  return (
    <aside
      className={`sidebar${collapsed ? " collapsed" : ""}`}
      style={{
        width: collapsed ? 72 : 260,
        background: "#fff",
        borderRight: "1px solid #e5eaf2",
        display: "flex",
        flexDirection: "column",
        transition: "width 0.2s cubic-bezier(.4,2,.6,1)",
        position: "relative"
      }}
    >
      {/* Collapse/Expand Button */}
      <div
        style={{
          position: "absolute",
          top: 16,
          right: collapsed ? -16 : -20,
          zIndex: 2,
          background: "#fff",
          border: "1px solid #e5eaf2",
          borderRadius: "50%",
          width: 32,
          height: 32,
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          cursor: "pointer",
          boxShadow: "0 2px 8px #eee"
        }}
        onClick={() => setCollapsed((v) => !v)}
        title={collapsed ? "Mở rộng" : "Thu gọn"}
      >
        <Icon icon={collapsed ? "lucide:chevron-right" : "lucide:chevron-left"} style={{ fontSize: 20 }} />
      </div>

      {/* Logo + Brand */}
      <div className="sidebar-brand" style={{
        padding: collapsed ? "24px 0" : 24,
        fontWeight: 700,
        fontSize: 18,
        display: "flex",
        alignItems: "center",
        gap: 12,
        justifyContent: collapsed ? "center" : "flex-start"
      }}>
        <img src="/picture/logotron.jpg" alt="Logo" style={{ width: "40px", height: "40px", borderRadius: 8 }} />
        {!collapsed && "HỌC VIỆN NGÂN HÀNG"}
      </div>
      {/* User Info */}
      <div style={{
        padding: collapsed ? "0 0 16px 0" : "0 24px 16px 24px",
        borderBottom: "1px solid #e5eaf2",
        marginBottom: 8,
        display: "flex",
        alignItems: "center",
        gap: 12,
        flexDirection: collapsed ? "column" : "row",
        justifyContent: "center"
      }}>
        <img
          src={user?.avatar || "https://app.banani.co/avatar1.jpeg"}
          alt="Avatar"
          style={{ width: 48, height: 48, borderRadius: "50%", objectFit: "cover", border: "2px solid #eaf3ff" }}
        />
        {!collapsed && (
          <div>
            <div style={{ fontWeight: 600, fontSize: 16 }}>{user?.fullname || "Tên người dùng"}</div>
            <div style={{ fontSize: 13, color: "#888" }}>{user?.id || "Mã người dùng"}</div>
            <div style={{ fontSize: 13, color: "#007bff", fontWeight: 500 }}>{user?.role || "Role"}</div>
          </div>
        )}
      </div>
      {/* Menu */}
      <nav className="sidebar-menu" style={{ flex: 1 }}>
        <div className="sidebar-section" style={{
          margin: "16px 0 0 0",
          padding: collapsed ? "0" : "0 24px",
          color: "#888",
          fontSize: 12,
          fontWeight: 600,
          textAlign: collapsed ? "center" : "left"
        }}>
          {!collapsed && "MAIN MENU"}
        </div>
        {menus.map(menu => (
          <div
            key={menu.path}
            className={`sidebar-link${location.pathname.startsWith(menu.path) ? " active" : ""}`}
            style={{
              padding: collapsed ? "12px 0" : "12px 24px",
              display: "flex",
              alignItems: "center",
              gap: collapsed ? 0 : 12,
              color: location.pathname.startsWith(menu.path) ? "#007bff" : "#222",
              fontWeight: location.pathname.startsWith(menu.path) ? 600 : 500,
              background: location.pathname.startsWith(menu.path) ? "#eaf3ff" : "none",
              borderRadius: 8,
              margin: collapsed ? "8px 0" : "8px 16px",
              cursor: "pointer",
              justifyContent: collapsed ? "center" : "flex-start"
            }}
            onClick={() => navigate(menu.path)}
            title={menu.label}
          >
            <Icon icon={menu.icon} style={{ fontSize: 20 }} />
            {!collapsed && menu.label}
          </div>
        ))}
        <div className="sidebar-section" style={{
          margin: "24px 0 0 0",
          padding: collapsed ? "0" : "0 24px",
          color: "#888",
          fontSize: 12,
          fontWeight: 600,
          textAlign: collapsed ? "center" : "left"
        }}>
          {!collapsed && "SYSTEM"}
        </div>
        {systemMenus.map(menu => (
          <div
            key={menu.path}
            className={`sidebar-link${location.pathname.startsWith(menu.path) ? " active" : ""}`}
            style={{
              padding: collapsed ? "12px 0" : "12px 24px",
              display: "flex",
              alignItems: "center",
              gap: collapsed ? 0 : 12,
              color: location.pathname.startsWith(menu.path) ? "#007bff" : "#222",
              fontWeight: location.pathname.startsWith(menu.path) ? 600 : 500,
              background: location.pathname.startsWith(menu.path) ? "#eaf3ff" : "none",
              borderRadius: 8,
              margin: collapsed ? "8px 0" : "0 16px",
              cursor: "pointer",
              justifyContent: collapsed ? "center" : "flex-start"
            }}
            onClick={() => navigate(menu.path)}
            title={menu.label}
          >
            <Icon icon={menu.icon} style={{ fontSize: 20 }} />
            {!collapsed && menu.label}
          </div>
        ))}
      </nav>
      {/* Logout */}
      <div style={{ margin: "auto 0 24px 0", padding: collapsed ? "0" : "0 24px", display: "flex", justifyContent: collapsed ? "center" : "flex-start" }}>
        <button
          style={{
            background: "none", border: "none", color: "#f44336", fontWeight: 600, display: "flex", alignItems: "center", gap: 8, cursor: "pointer"
          }}
          onClick={handleLogout}
        >
          <Icon icon="lucide:log-out" style={{ fontSize: 20 }} />
          {!collapsed && "Sign out"}
        </button>
      </div>
    </aside>
  );
};

export default SidebarComponent;