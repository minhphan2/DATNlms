import React from "react";
import "../assets/CSS/CourseDetail.css";// Bạn nên copy các style vào file này hoặc dùng styled-components
import { useEffect, useState } from "react";
import { getCourses, getCoursesById } from "../api/Courseapi.jsx";
import { getSection } from "../api/Sectionapi.jsx";
import { useParams } from "react-router-dom";



const CourseDetail = () => {
  const [sections, setSections] = useState([]);
  const [loading, setLoading] = useState(true);
  const [courses, setCourses] = useState([]);
  const courseName = courses;
  const token = localStorage.getItem('token');
  const {courseId} = useParams();

  useEffect(() => {
    if (!courseId) return;
    // Lấy thông tin course
    getCoursesById(courseId, token)
      .then((data) => {
        setCourses(data);
     // hoặc data.title, tuỳ API trả về
      })
      .catch(() => setCourses(""));

    // Lấy section
    getSection(token, courseId)
      .then((data) => {
        setSections(data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [courseId, token]);





  return (
    <div className="page-root">
      <nav className="top-nav">
        <div className="nav-left">
          <a className="nav-link">Home</a>
          <a className="nav-link">Dashboard</a>
          <a className="nav-link">My courses</a>
        </div>
        <div className="nav-right">
          <div>
            <span className="iconify" data-icon="lucide:message-square" style={{ fontSize: 18 }}></span>
          </div>
          <div className="user-badge">P</div>
        </div>
      </nav>

      <div className="sub-header">
        <div className="lang-select">
          ENGLISH (EN)
          <span className="iconify" data-icon="lucide:chevron-down" style={{ fontSize: 14 }}></span>
        </div>
      </div>

      <div className="content-shell">
        <div className="main-layout">
          <aside className="sidebar">
            <div className="sidebar-close-btn">
              <span className="iconify" data-icon="lucide:x" style={{ fontSize: 18 }}></span>
            </div>
            <div>
              <div className="sidebar-section-title">
                <span className="iconify" data-icon="lucide:chevron-down" style={{ fontSize: 16 }}></span>
                General
              </div>
              <ul className="sidebar-list">
                <li className="sidebar-item">Announcements</li>
                <li className="sidebar-item">Slides Pháp luật đại cương</li>
                <li className="sidebar-item">Đề cương-Pháp luật đại cương...</li>
              </ul>
            </div>
          </aside>

          <main className="content-area">
            <div>
              <div className="breadcrumbs">
                <a className="breadcrumb-link">Khoa Luật</a>
                <span className="breadcrumb-sep">/</span>
                <a className="breadcrumb-link">Khoa Luật - BM Pháp luật cơ sở</a>
                <span className="breadcrumb-sep">/</span>
                <span>HK01-22-23-006</span>
              </div>
              <h1 className="page-title">{courseId} - {courses.title}</h1>
            </div>
            <div className="tabs">
              <div className="tab-item active">Course</div>
              <div className="tab-item">Participants</div>
              <div className="tab-item">Grades</div>
              <div className="tab-item">Competencies</div>
            </div>

            <div className="content-header">
              <div className="section-name">
                <span className="iconify" data-icon="lucide:chevron-down" style={{ fontSize: 18 }}></span>
                Sections
              </div>
              <div className="collapse-link">Collapse all</div>
            </div>

            {loading ? (
              <div>Loading sections...</div>
            ) : (
              <div className="activity-list">
                {sections.map((section) => (
                  <div className="activity-card" key={section.id}>
                    <div className="activity-left">
                      <div className="activity-icon-box bg-blue">
                        <span className="iconify" data-icon="lucide:folder" style={{ fontSize: 22 }}></span>
                      </div>
                      <div className="activity-details">
                        <span className="activity-type">{section.type?.toUpperCase() || "SECTION"}</span>
                        <span className="activity-title">{section.title}</span>
                        <span className="activity-title">Chương {section.position}</span>
                      </div>
                    </div>
                    {section.canMarkDone && (
                      <button className="mark-done-btn">Mark as done</button>
                    )}
                  </div>
                ))}
              </div>
            )}
          </main>
        </div>

        <footer className="footer">
          <div className="footer-content">
            <div className="footer-col">
              <span className="footer-link">Website Trường</span>
              <span className="footer-link">Cổng thông tin đào tạo</span>
              <span className="footer-link">Trang tuyển sinh</span>
              <span className="footer-link">Moodle.com</span>
            </div>
            <div className="footer-col">
              <span>Số 12, đường Chùa Bộc, Quận Đống Đa, Hà Nội</span>
              <span>📞 : +84 243 852 1305</span>
              <span>✉️ : truyenthong@hvnh.edu.vn</span>
            </div>
            <div className="footer-col">
              <div className="social-icon">
                <span className="iconify" data-icon="lucide:facebook" style={{ fontSize: 18 }}></span>
              </div>
            </div>
          </div>
        </footer>

        <div className="floating-help">
          <span className="iconify" data-icon="lucide:help-circle" style={{ fontSize: 22 }}></span>
        </div>
      </div>
    </div>
  );
};

export default CourseDetail;