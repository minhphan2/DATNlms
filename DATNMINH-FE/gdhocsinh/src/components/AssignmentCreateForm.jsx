import React, { useState, useEffect } from "react";
import { createAssignmentWithFiles, getAssignmentAttachments, downloadAssignmentAttachment, getSubmissionCount, getStudentCount } from "../api/Assignmentapi";
import { Icon } from "@iconify/react";
import { useNavigate } from "react-router-dom";

function AssignmentCreateForm({ courseId, onCreated, assignments }) {
  const [title, setTitle] = useState("");
  const [deadline, setDeadline] = useState("");
  const [instructions, setInstructions] = useState("");
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [assignmentsWithFiles, setAssignmentsWithFiles] = useState([]);
  const [submissionCounts, setSubmissionCounts] = useState({});
  const [studentCount, setStudentCount] = useState(0);
  const navigate = useNavigate();

  // Khi assignments thay đổi, fetch attachments cho từng assignment
  useEffect(() => {
    const fetchAll = async () => {
      const token = sessionStorage.getItem("token");
      // Fetch attachments
      const updated = await Promise.all(
        (assignments || []).map(async (a) => {
          if (a.attachments && Array.isArray(a.attachments)) return a;
          const attachments = await getAssignmentAttachments(token, a.assignmentId);
          return { ...a, attachments };
        })
      );
      setAssignmentsWithFiles(updated);

      // Fetch tổng số học sinh
      const total = await getStudentCount(token, courseId);
      console.log("Total students:", total);
      setStudentCount(total);

      // Fetch số bài đã nộp cho từng assignment
      const counts = {};
      for (let a of assignments || []) {
        counts[a.assignmentId] = await getSubmissionCount(token, a.assignmentId);
        console.log(`Assignment ${a.assignmentId} submission count:`, counts[a.assignmentId]);
      }
      setSubmissionCounts(counts);
    };
    if (assignments && assignments.length > 0) fetchAll();
    else {
      setAssignmentsWithFiles([]);
      setSubmissionCounts({});
      setStudentCount(0);
    }
  }, [assignments, courseId]);

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

  // Tạo assignment và upload nhiều file
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    const token = sessionStorage.getItem("token");
    const data = { courseId, title, deadline, instructions };
    try {
      const assignment = await createAssignmentWithFiles(data, files, token);
      if (onCreated) onCreated(assignment);
      setTitle("");
      setDeadline("");
      setInstructions("");
      setFiles([]);
      alert("Tạo assignment thành công!");
    } catch (err) {
      alert("Tạo assignment thất bại!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit} style={{
        background: "#fff",
        borderRadius: 12,
        boxShadow: "0 2px 8px #eee",
        padding: 24,
        marginBottom: 24,
        display: "flex",
        flexDirection: "column",
        gap: 12,
        maxWidth: 500
      }}>
        <div style={{ fontWeight: 700, fontSize: 18, marginBottom: 8 }}>
          <Icon icon="lucide:plus-circle" style={{ color: "#007bff", marginRight: 8 }} />
          Tạo Assignment mới
        </div>
        <input
          value={title}
          onChange={e => setTitle(e.target.value)}
          placeholder="Tiêu đề bài tập"
          required
          style={{ padding: 8, borderRadius: 6, border: "1px solid #e5eaf2" }}
        />
        <textarea
          value={instructions}
          onChange={e => setInstructions(e.target.value)}
          placeholder="Hướng dẫn (tuỳ chọn)"
          rows={2}
          style={{ padding: 8, borderRadius: 6, border: "1px solid #e5eaf2" }}
        />
        <input
          type="datetime-local"
          value={deadline}
          onChange={e => setDeadline(e.target.value)}
          required
          style={{ padding: 8, borderRadius: 6, border: "1px solid #e5eaf2" }}
        />
        <input
          type="file"
          multiple
          onChange={e => setFiles([...e.target.files])}
          style={{ marginTop: 4, border: "1px solid #e5eaf2", borderRadius: 6, padding: 8 }}
        />
        <button type="submit" disabled={loading} style={{
          background: "#007bff",
          color: "#fff",
          border: "none",
          borderRadius: 8,
          padding: "10px 0",
          fontWeight: 600,
          fontSize: 16,
          marginTop: 8,
          cursor: "pointer"
        }}>
          {loading ? "Đang tạo..." : "Tạo Assignment"}
        </button>
      </form>

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
            <div style={{ marginTop:"auto", fontSize: 14, color: "#007bff", fontWeight: 600 }}>
              Đã nộp: {submissionCounts[a.assignmentId] || 0}/{studentCount}


              <button
              onClick={() => navigate(`/teacher/assignment/${a.assignmentId}`)}
              style={{
                marginLeft: "150px",
                background: "none",
                color: "#007bff",
                border: "none",
                padding: 0,
                fontSize: 15,
                fontWeight: 500,
                cursor: "pointer",
                textAlign: "left"
              }}
            >
              Xem chi tiết
            </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AssignmentCreateForm;