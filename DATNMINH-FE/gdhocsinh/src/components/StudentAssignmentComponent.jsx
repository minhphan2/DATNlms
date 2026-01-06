import React, { useState, useEffect } from "react";
import { createAssignmentWithFiles, getAssignmentAttachments, downloadAssignmentAttachment } from "../api/Assignmentapi";
import { Icon } from "@iconify/react";

function StudentAssignmentComponent({ courseId, onCreated, assignments }) {
  const [title, setTitle] = useState("");
  const [deadline, setDeadline] = useState("");
  const [instructions, setInstructions] = useState("");
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [assignmentsWithFiles, setAssignmentsWithFiles] = useState([]);

  // Khi assignments thay đổi, fetch attachments cho từng assignment
  useEffect(() => {
    const fetchAttachments = async () => {
      const token = sessionStorage.getItem("token");
      const updated = await Promise.all(
        (assignments || []).map(async (a) => {
          // Nếu đã có attachments thì giữ nguyên, nếu chưa thì fetch
          if (a.attachments && Array.isArray(a.attachments)) return a;
          const attachments = await getAssignmentAttachments(token, a.assignmentId);
          return { ...a, attachments };
        })
      );
      setAssignmentsWithFiles(updated);
    };
    if (assignments && assignments.length > 0) fetchAttachments();
  }, [assignments]);

  // Download file đính kèm
  const handleDownloadAttachment = async (attachmentId, fileName) => {
    const token = sessionStorage.getItem('token');
    try {
      const blob = await downloadAssignmentAttachment(token, attachmentId);
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      a.remove();
      window.URL.revokeObjectURL(url);
    } catch (e) {
      alert("Tải file thất bại!");
    }
  };


  return (
    <div>
      {/* Danh sách assignment dạng card */}
      <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(340px, 1fr))", gap: 24 }}>
        {assignmentsWithFiles.map(a => (
          <div key={a.assignmentId} style={{
            background: "#fff",
            borderRadius: 12,
            boxShadow: "0 2px 8px #eee",
            padding: 20,
            display: "flex",
            flexDirection: "column",
            gap: 8
          }}>
            <div style={{ fontWeight: 700, fontSize: 18, color: "#007bff" }}>{a.title}</div>
            <div style={{ color: "#888", fontSize: 15 }}>Hạn cuối: {a.deadline}</div>
            {a.instructions && <div style={{ fontSize: 15 }}>{a.instructions}</div>}
            {/* Hiển thị danh sách file đính kèm */}
            {a.attachments && a.attachments.length > 0 && (
              <div>
                {a.attachments.map(att => (
                  <div key={att.attachmentId}>
                    <a
                      href="#"
                      style={{ color: "#007bff", fontWeight: 500, fontSize: 15 }}
                      onClick={e => {
                        e.preventDefault();
                        handleDownloadAttachment(att.attachmentId, att.fileName);
                      }}
                    >
                      <Icon icon="lucide:paperclip" /> {att.fileName}
                    </a>
                  </div>
                ))}
              </div>
            )}
            <div style={{ fontSize: 14, color: "#888" }}>Tạo lúc: {a.createdAt}</div>
            <div style={{ fontSize: 14, color: "#888" }}>Điểm tối đa: {a.maxScore}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default StudentAssignmentComponent;