import React, { useState, useEffect } from "react";
import { getAssignmentsByCourse, getAssignmentAttachments, downloadAssignmentAttachment, getSubmissionByAssignmentAndStudent, submitAssignment } from "../api/Assignmentapi";
import { Icon } from "@iconify/react";

function StudentAssignmentComponent({ courseId, studentId }) {
  console.log("courseId:", courseId, "studentId:", studentId);
  const [assignmentsWithFiles, setAssignmentsWithFiles] = useState([]);
  const [submissions, setSubmissions] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [selectedAssignment, setSelectedAssignment] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchAssignmentsAndAttachments = async () => {
      const token = sessionStorage.getItem("token");
      const assignments = await getAssignmentsByCourse(token, courseId);

      // Lấy attachments cho từng assignment
      const updated = await Promise.all(
        (assignments || []).map(async (a) => {
          const attachments = await getAssignmentAttachments(token, a.assignmentId);
          return { ...a, attachments };
        })
      );
      setAssignmentsWithFiles(updated);

      // Lấy submission cho từng assignment
      const subs = {};
      for (let a of assignments) {
        subs[a.assignmentId] = await getSubmissionByAssignmentAndStudent(token, a.assignmentId, studentId);
      }
      setSubmissions(subs);
    };
    if (courseId && studentId) fetchAssignmentsAndAttachments();
  }, [courseId, studentId]);

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

  // Mở modal nộp bài
  const openModal = (assignment) => {
    setSelectedAssignment(assignment);
    setSelectedFile(null);
    setShowModal(true);
  };

  // Nộp bài
  const handleSubmit = async () => {
    if (!selectedFile || !selectedAssignment) return;
    setLoading(true);
    const token = sessionStorage.getItem("token");
    try {
      await submitAssignment(token, selectedAssignment.assignmentId, studentId, selectedFile);
      setShowModal(false);
      window.location.reload(); // hoặc fetch lại submissions
    } catch (e) {
      alert("Nộp bài thất bại!");
    }
    setLoading(false);
  };

  return (
    <div>
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
            {/* Nút nộp bài */}
            <div style={{ marginTop: 12 }}>
              {submissions[a.assignmentId] ? (
                <button disabled style={{ background: "#ccc", color: "#fff", border: "none", borderRadius: 6, padding: "8px 20px" }}>
                  Đã nộp
                </button>
              ) : (
                <button onClick={() => openModal(a)} style={{ background: "#007bff", color: "#fff", border: "none", borderRadius: 6, padding: "8px 20px" }}>
                  Nộp bài
                </button>
              )}
            </div>
          </div>
        ))}
      </div>

      {/* Modal nộp bài */}
      {showModal && (
        <div style={{
          position: "fixed", top: 0, left: 0, right: 0, bottom: 0,
          background: "rgba(0,0,0,0.3)", display: "flex", alignItems: "center", justifyContent: "center", zIndex: 1000
        }}>
          <div style={{
            background: "#fff", borderRadius: 12, padding: 32, minWidth: 320, boxShadow: "0 2px 12px #aaa",
            display: "flex", flexDirection: "column", alignItems: "center"
          }}>
            <div style={{ fontWeight: 600, fontSize: 20, marginBottom: 16 }}>Nộp bài: {selectedAssignment?.title}</div>
            <input
              type="file"
              onChange={e => setSelectedFile(e.target.files[0])}
              style={{ marginBottom: 16 }}
            />
            <div style={{ display: "flex", gap: 12 }}>
              <button
                onClick={handleSubmit}
                disabled={!selectedFile || loading}
                style={{ background: "#007bff", color: "#fff", border: "none", borderRadius: 6, padding: "8px 20px" }}
              >
                Xác nhận nộp
              </button>
              <button
                onClick={() => setShowModal(false)}
                style={{ background: "#ccc", color: "#333", border: "none", borderRadius: 6, padding: "8px 20px" }}
              >
                Hủy
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default StudentAssignmentComponent;