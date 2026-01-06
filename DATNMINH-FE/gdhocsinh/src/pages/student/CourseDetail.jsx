import React, { useEffect, useState } from "react";
import "../../assets/CSS/CourseDetail.css";
import { getCoursesById } from "../../api/Courseapi.jsx";
import { getSection } from "../../api/Sectionapi.jsx";
import { useParams, useNavigate } from "react-router-dom";
import Headder from "../../components/Headder.jsx";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import ListStudent from "../../components/ListStudent.jsx";
import StudentAssignmentComponent from "../../components/StudentAssignmentComponent.jsx";
import { Icon } from "@iconify/react";

const tabList = [
  { label: "Course" },
  { label: "Participants" },
  { label: "Assignments" },
  { label: "Competencies" },
];

const CourseDetail = () => {
  const [sections, setSections] = useState([]);
  const [loading, setLoading] = useState(true);
  const [course, setCourse] = useState({});
  const [activeTab, setActiveTab] = useState(0);
  const token = sessionStorage.getItem('token');
  const { courseId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (!courseId) return;
    getCoursesById(courseId, token)
      .then((data) => setCourse(data))
      .catch(() => setCourse({}));

    getSection(token, courseId)
      .then((data) => {
        setSections(data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [courseId, token]);

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ padding: "32px", height: "100%" }}>
          {/* Breadcrumbs */}
          <div style={{ color: "#888", fontSize: 14, marginBottom: 4 }}>
            <span>Khoa Luật</span> / <span>HK01-22-23-006</span> / <span>{course.title || "Tên khóa học"}</span>
          </div>
          {/* Title */}
          <div style={{ fontSize: 28, fontWeight: 700, marginBottom: 8 }}>
            {courseId} - {course.title || "Tên khóa học"}
          </div>
          {/* Tabs */}
          <div style={{ position: "relative", marginBottom: 16 }}>
            <div style={{ display: "flex", gap: 32, borderBottom: "1px solid #e5eaf2" }}>
              {tabList.map((tab, idx) => (
                <div
                  key={tab.label}
                  onClick={() => setActiveTab(idx)}
                  style={{
                    padding: "8px 0",
                    color: activeTab === idx ? "#007bff" : "#888",
                    fontWeight: activeTab === idx ? 600 : 500,
                    cursor: "pointer",
                    fontSize: 18,
                    background: "none",
                    border: "none",
                    outline: "none",
                    position: "relative",
                    transition: "color 0.2s"
                  }}
                >
                  {tab.label}
                  {activeTab === idx && (
                    <div style={{
                      height: 3,
                      background: "#007bff",
                      borderRadius: 2,
                      position: "absolute",
                      left: 0,
                      right: 0,
                      bottom: -9,
                      width: "100%",
                      margin: "auto"
                    }} />
                  )}
                </div>
              ))}
            </div>
          </div>

          {/* Slide content */}
          <div style={{
            overflow: "hidden",
            minHeight: 200,
            position: "relative"
          }}>
            <div style={{
              display: "flex",
              width: `${tabList.length * 100}%`,
              transform: `translateX(-${activeTab * (100 / tabList.length)}%)`,
              transition: "transform 0.4s cubic-bezier(.4,2,.6,1)"
            }}>
              {/* Course Tab */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0 }}>
                {/* Section List Header */}
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 12 }}>
                  <div style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
                    <Icon icon="lucide:layers" style={{ color: "#007bff", fontSize: 24 }} />
                    Danh sách chương
                  </div>
                  <div style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Collapse all</div>
                </div>
                {/* Section List */}
                {loading ? (
                  <div>Đang tải danh sách chương...</div>
                ) : (
                  <div style={{ display: "flex", flexDirection: "column", gap: 20 }}>
                    {sections.map((section) => (
                      <div
                        key={section.id}
                        style={{
                          background: "#fff",
                          borderRadius: 12,
                          boxShadow: "0 2px 8px #eee",
                          padding: 24,
                          display: "flex",
                          alignItems: "center",
                          cursor: "pointer",
                          transition: "box-shadow 0.2s",
                        }}
                        onClick={() => navigate(`/section/${section.id}`)}
                      >
                        <div style={{ display: "flex", alignItems: "center", gap: 20, flex: 1 }}>
                          <div style={{
                            width: 48,
                            height: 48,
                            background: "#eaf3ff",
                            borderRadius: 12,
                            display: "flex",
                            alignItems: "center",
                            justifyContent: "center"
                          }}>
                            <Icon icon="lucide:folder" style={{ fontSize: 28, color: "#007bff" }} />
                          </div>
                          <div>
                            <div style={{ color: "#007bff", fontWeight: 700, fontSize: 16 }}>
                              {section.type?.toUpperCase() || "SECTION"}
                            </div>
                            <div style={{ fontWeight: 600, fontSize: 18 }}>{section.title}</div>
                            <div style={{ color: "#888", fontSize: 15 }}>Chương {section.position}</div>
                          </div>
                        </div>
                        {section.canMarkDone && (
                          <button style={{
                            background: "#007bff",
                            color: "#fff",
                            border: "none",
                            borderRadius: 8,
                            padding: "8px 20px",
                            fontWeight: 600,
                            cursor: "pointer"
                          }}>
                            Mark as done
                          </button>
                        )}
                      </div>
                    ))}
                  </div>
                )}
              </div>
              {/* Participants Tab */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0, padding: 24 }}>
                <ListStudent courseId={courseId}  token={token}/>
              </div>
              {/* Grades Tab */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0, padding: 24 }}>
                <StudentAssignmentComponent courseId={courseId} token={token}/>
              </div>
              {/* Competencies Tab */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0, padding: 24 }}>
                <div style={{ color: "#888", fontSize: 18 }}>Competencies content here...</div>
              </div>
            </div>
          </div>
        </div>

        {/* Footer */}
        <footer style={{
          textAlign: "center",
          color: "#888",
          fontSize: 14,
          margin: "32px 0 0 0",
          background: "#fff",
          borderTop: "1px solid #e5eaf2",
          padding: "16px 0"
        }}>
          <div>
            <span className="footer-link" style={{ marginRight: 16 }}>Website Trường</span>
            <span className="footer-link" style={{ marginRight: 16 }}>Cổng thông tin đào tạo</span>
            <span className="footer-link" style={{ marginRight: 16 }}>Trang tuyển sinh</span>
            <span className="footer-link">Moodle.com</span>
          </div>
          <div style={{ marginTop: 8 }}>
            Số 12, đường Chùa Bộc, Quận Đống Đa, Hà Nội | 📞 +84 243 852 1305 | ✉️ truyenthong@hvnh.edu.vn
          </div>
          <div style={{ marginTop: 8 }}>
            <Icon icon="lucide:facebook" style={{ fontSize: 18, marginRight: 8 }} />
          </div>
        </footer>
        {/* Floating Help */}
        <div style={{
          position: "fixed",
          bottom: 32,
          right: 32,
          background: "#007bff",
          color: "#fff",
          borderRadius: "50%",
          width: 48,
          height: 48,
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          boxShadow: "0 2px 8px #aaa",
          cursor: "pointer",
          zIndex: 100
        }}>
          <Icon icon="lucide:help-circle" style={{ fontSize: 28 }} />
        </div>
      </div>
    </div>
  );
};

export default CourseDetail;