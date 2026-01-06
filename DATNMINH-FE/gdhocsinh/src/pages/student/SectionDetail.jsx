import React, { useEffect, useState } from "react";
import "../../assets/CSS/CourseDetail.css";
import { getLessonBySectionId } from "../../api/Lessonapi.jsx";
import { getSection } from "../../api/Sectionapi.jsx";
import { useParams, useNavigate } from "react-router-dom";
import LessonCard from "../../components/LessonCard.jsx";
import Headder from "../../components/Headder.jsx";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import { Icon } from "@iconify/react";
import MaterialList from "../../components/MaterialList.jsx";

const SectionDetail = () => {
  const [sections, setSections] = useState([]);
  const [loading, setLoading] = useState(true);
  const [lessons, setLessons] = useState([]);
  const [openMaterialLessonId, setOpenMaterialLessonId] = useState(null);
  const token = sessionStorage.getItem('token');
  const { sectionId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (!sectionId) return;

    // Lấy thông tin lesson
    getLessonBySectionId(token, sectionId)
      .then((data) => setLessons(data))
      .catch(() => setLessons([]));

    // Lấy section
    getSection(token, sectionId)
      .then((data) => {
        setSections(Array.isArray(data) ? data : [data]);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [token, sectionId]);

  // Lấy tên section
  const sectionTitle = sections.find(s => String(s.id) === String(sectionId))?.title || "Không có tiêu đề";

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>

        <div style={{ padding: "32px" , height: "100%" }}>
          {/* Breadcrumbs */}
          <div style={{ color: "#888", fontSize: 14, marginBottom: 4 }}>
            <span>Khoa Luật</span> / <span>Khoa Luật - BM Pháp luật cơ sở</span> / <span>HK01-22-23-006</span>
          </div>
          {/* Title */}
          <div style={{ fontSize: 28, fontWeight: 700, marginBottom: 8 }}>
            {sectionId} - {sectionTitle}
          </div>
          {/* Tabs */}
          {/* Section Lessons Header */}
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 12 }}>
            <div style={{ display: "flex", alignItems: "center", gap: 8, fontWeight: 600, fontSize: 20 }}>
              Danh sách bài học
            </div>
            <div style={{ color: "#007bff", fontWeight: 500, cursor: "pointer" }}>Collapse all</div>
          </div>

          {/* Lessons List */}
          {loading ? (
            <div>Loading lessons...</div>
          ) : (
            <div style={{ display: "flex", flexDirection: "column", gap: 20 }}>
        {lessons.map((lesson) => (
          <React.Fragment key={lesson.lessonId}>
            <LessonCard
              lesson={lesson}
              isOpen={openMaterialLessonId === lesson.lessonId}
              onToggleMaterial={() =>
                setOpenMaterialLessonId(
                  openMaterialLessonId === lesson.lessonId ? null : lesson.lessonId
                )
              }
            />
            {openMaterialLessonId === lesson.lessonId && (
              <MaterialList lessonId={lesson.lessonId} />
            )}
          </React.Fragment>
        ))}
      </div>
          )}
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

export default SectionDetail;