import React, { useEffect, useState } from "react";
import "../../assets/CSS/CourseDetail.css";
import { useParams, useNavigate } from "react-router-dom";
import { getCourseDetail, getAssignments } from "../../api/Courseapi";
import AssignmentCreateForm from "../../components/AssignmentCreateForm";
import CourseTree from "../../components/ListSECLESMATteacher";
import SidebarComponent from "../../components/SidebarComponent";
import { Icon } from "@iconify/react";

const tabList = [
  { label: "Cấu trúc & tài liệu" },
  { label: "Bài tập" },
  { label: "Thông tin khóa học" }
];

function TeacherCourseDetail() {
  const { courseId } = useParams();
  const [course, setCourse] = useState({});
  const [assignments, setAssignments] = useState([]);
  const [activeTab, setActiveTab] = useState(0);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const token = sessionStorage.getItem("token");

  useEffect(() => {
    Promise.all([
      getCourseDetail(courseId, token),
      getAssignments(courseId, token)
    ]).then(([courseData, assignmentData]) => {
      setCourse(courseData);
      setAssignments(assignmentData);
      setLoading(false);
    });
  }, [courseId, token]);

  const handleAssignmentCreated = (newAssignment) => {
    setAssignments(prev => [...prev, newAssignment]);
  };

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
              {/* Tab 1: Cấu trúc & tài liệu */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0 }}>
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 12 }}>
                  <div style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
                    <Icon icon="lucide:layers" style={{ color: "#007bff", fontSize: 24 }} />
                    Cấu trúc khóa học & tài liệu
                  </div>
                  <div style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Collapse all</div>
                </div>
                <CourseTree courseId={courseId} />
              </div>
              {/* Tab 2: Bài tập */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0, padding: 24 }}>
                <div style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20, marginBottom: 12 }}>
                  <Icon icon="lucide:clipboard-list" style={{ color: "#007bff", fontSize: 24 }} />
                  Quản lý bài tập
                </div>
                <AssignmentCreateForm
                  courseId={courseId}
                  assignments={assignments}
                  onCreated={newAssignment => setAssignments(prev => [...prev, newAssignment])}
                />
              </div>
              {/* Tab 3: Thông tin khóa học */}
              <div style={{ width: `${100 / tabList.length}%`, flexShrink: 0, padding: 24 }}>
                <div style={{ fontWeight: 600, fontSize: 20, marginBottom: 12 }}>
                  <Icon icon="lucide:info" style={{ color: "#007bff", fontSize: 24 }} />
                  Thông tin khóa học
                </div>
                <div><b>Mã môn:</b> {course.courseName}</div>
                <div><b>Giáo viên:</b> {course.teacherName}</div>
                <div><b>Mô tả:</b> {course.description}</div>
                <div><b>Thể loại:</b> {course.category}</div>
                <div><b>Trạng thái:</b> {course.status}</div>
                <div><b>Ngày tạo:</b> {course.createdAt}</div>
              </div>
            </div>
          </div>
        </div>
      
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
}

export default TeacherCourseDetail;