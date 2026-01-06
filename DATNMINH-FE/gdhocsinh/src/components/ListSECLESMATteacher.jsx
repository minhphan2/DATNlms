import React, { useState, useEffect } from "react";
import { getSection } from "../api/Sectionapi";
import { getLessonBySectionId } from "../api/Lessonapi";
import MaterialList from "./MaterialList"; // Import MaterialList
import MaterialUploadForm from "./MaterialUploadForm";

// Card cho Section
function SectionCard({ section, children, onClick, isOpen }) {
  return (
    <div style={{
      background: "#fff",
      borderRadius: 8,
      boxShadow: "0 2px 8px #e5eaf2",
      marginBottom: 16,
      padding: 16
    }}>
      <div
        style={{
          fontWeight: 700,
          fontSize: 18,
          marginBottom: 8,
          color: isOpen ? "#007bff" : "#222",
          cursor: "pointer",
          userSelect: "none"
        }}
        onClick={onClick}
      >
        {section.title}
      </div>
      {children}
    </div>
  );
}

// Card cho Lesson
function LessonCard({ lesson, children, onClick, isOpen }) {
  return (
    <div style={{
      background: "#f6faff",
      borderRadius: 6,
      marginBottom: 8,
      padding: "8px 16px",
      marginLeft: 16,
      border: isOpen ? "2px solid #007bff" : "1px solid #e5eaf2"
    }}>
      <div
        style={{
          fontWeight: 600,
          fontSize: 16,
          color: "#007bff",
          cursor: "pointer",
          userSelect: "none"
        }}
        onClick={onClick}
      >
        {lesson.title}
      </div>
      {children}
    </div>
  );
}

function CourseTree({ courseId }) {
  const [sections, setSections] = useState([]);
  const [openSection, setOpenSection] = useState(null);
  const [lessons, setLessons] = useState({});
  const [openLesson, setOpenLesson] = useState(null);
  const [reload, setReload] = useState(0);

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    getSection(token, courseId).then(setSections);
  }, [courseId]);

  const handleSectionClick = (sectionId) => {
    if (openSection === sectionId) {
      setOpenSection(null);
      setOpenLesson(null); // Đóng lesson khi đóng section
    } else {
      setOpenSection(sectionId);
      setOpenLesson(null); // Reset lesson khi mở section mới
      if (!lessons[sectionId]) {
        const token = sessionStorage.getItem("token");
        getLessonBySectionId(token, sectionId).then(data =>
          setLessons(prev => ({ ...prev, [sectionId]: data }))
        );
      }
    }
  };

  const handleLessonClick = (lessonId) => {
    setOpenLesson(openLesson === lessonId ? null : lessonId);
  };

  return (
    <div>
      {sections.map(section => (
        <SectionCard
          key={section.id}
          section={section}
          onClick={() => handleSectionClick(section.id)}
          isOpen={openSection === section.id}
        >
          {openSection === section.id && lessons[section.id] && lessons[section.id].map(lesson => (
            <LessonCard
              key={lesson.lessonId}
              lesson={lesson}
              onClick={e => {
                e.stopPropagation();
                handleLessonClick(lesson.lessonId);
              }}
              isOpen={openLesson === lesson.lessonId}
            >
              {openLesson === lesson.lessonId && (
                <>
                
                <MaterialList lessonId={lesson.lessonId} reload={reload} />
                <h3>Đăng tài liệu của bạn</h3>
                <MaterialUploadForm lessonId={lesson.lessonId} 
                onUploaded={() => setReload(r => r + 1)}/>
                </>
              )}
            </LessonCard>
          ))}
        </SectionCard>
      ))}
    </div>
  );
}

export default CourseTree;