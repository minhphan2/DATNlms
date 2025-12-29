import React from "react";
import { Icon } from "@iconify/react";

function LessonCard({ lesson, isOpen, onToggleMaterial }) {
  return (
    <div className="activity-card" style={{ position: "relative" }}>
      <div className="activity-left">
        <div className="activity-icon-box bg-blue">
          <span className="iconify" data-icon="lucide:folder" style={{ fontSize: 22 }}></span>
        </div>
        <div className="activity-details">
          <span className="activity-type">{lesson.type?.toUpperCase() || "Lesson"}</span>
          <span className="activity-title">{lesson.title}</span>
          <span className="activity-title">Bài {lesson.position}</span>
        </div>
      </div>
      {/* Nút tài liệu góc phải */}
      <div
        style={{
          position: "absolute",
          top: 16,
          right: 16,
          cursor: "pointer",
          display: "flex",
          alignItems: "center",
          gap: 4,
          fontWeight: 500,
        }}
        onClick={onToggleMaterial}
      >
        Tài liệu
        <Icon
          icon={isOpen ? "lucide:chevron-up" : "lucide:chevron-down"}
          style={{ fontSize: 18 }}
        />
      </div>
      {lesson.canMarkDone && (
        <button className="mark-done-btn">Mark as done</button>
      )}
    </div>
  );
}

export default LessonCard;