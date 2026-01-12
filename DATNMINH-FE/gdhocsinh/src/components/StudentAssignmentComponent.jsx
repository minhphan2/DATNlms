import React, { useState, useEffect } from "react";
import { getAssignmentsByCourse, getAssignmentAttachments, downloadAssignmentAttachment, getSubmissionByAssignmentAndStudent } from "../api/Assignmentapi";
import { Icon } from "@iconify/react";
import { uploadSubmissionAttachment, createSubmissionWithFiles } from "../api/Submissionapi";

function StudentAssignmentComponent({ courseId, studentId }) {
  const [assignmentsWithFiles, setAssignmentsWithFiles] = useState([]);
  const [submissions, setSubmissions] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [selectedAssignment, setSelectedAssignment] = useState(null);
  const [selectedFiles, setSelectedFiles] = useState(null);
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
        console.log("Submission for assignment", a.assignmentId, ":", subs[a.assignmentId]);
      }
      setSubmissions(subs);
    };
    if (courseId && studentId) fetchAssignmentsAndAttachments();
  }, [courseId, studentId]);

  // Nộp bài mới (nộp nhiều file)
  const handleSubmit = async () => {
    console.log("studentId:", studentId);
    if (!selectedFiles || !selectedAssignment) return;
    setLoading(true);
    const token = sessionStorage.getItem("token");
    console.log ("token:", token);
    try {
      if (!submissions[selectedAssignment.assignmentId]) {
        // Chưa nộp: tạo submission mới dùng createSubmissionWithFiles
        const formData = new FormData();
        formData.append('assignmentId', selectedAssignment.assignmentId);
        formData.append('studentId', studentId);
        for (let i = 0; i < selectedFiles.length; i++) {
          formData.append('files', selectedFiles[i]);
        }
        await createSubmissionWithFiles(formData, Array.from(selectedFiles), token);
        alert("Nộp bài thành công!");
        setShowModal(false);
        window.location.reload();
      } else {
        // Đã nộp: upload thêm file
        const submissionId = submissions[selectedAssignment.assignmentId].submissionId;
        await uploadSubmissionAttachment(token, submissionId, selectedFiles);
        setShowModal(false);
        window.location.reload();
      }
    } catch (e) {
      alert("Nộp bài thất bại!");
    }
    setLoading(false);
  };

  // Mở modal nộp bài
  const openModal = (assignment) => {
    setSelectedAssignment(assignment);
    setSelectedFiles(null);
    setShowModal(true);
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
            gap: 8,
            height:"320px"
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
                        downloadAssignmentAttachment(att.attachmentId, att.fileName);
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
            <div style={{ marginTop: "auto" }}>
              {submissions[a.assignmentId] ? (
              <button disabled style={{ background: "#ccc", color: "#fff", border: "none", borderRadius: 6, padding: "8px 20px" }}>
                Đã nộp
              </button>
            ) : (
              <button onClick={() => openModal(a)} style={{alignItems:"flex-end", marginBottom:"auto", background: "#007bff", color: "#fff", border: "none", borderRadius: 6, padding: "8px 20px" }}>
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
          paddingLeft: "25%",
          paddingBottom: "25%",
          background: "rgba(0,0,0,0.3)", display: "flex", alignItems: "center", justifyContent: "center", zIndex: 1000
        }}>
          <div style={{
            background: "#fff", borderRadius: 12, padding: 32, minWidth: 320, boxShadow: "0 2px 12px #aaa",
            display: "flex", flexDirection: "column", alignItems: "center"
          }}>
            <div style={{ fontWeight: 600, fontSize: 20, marginBottom: 16 }}>Nộp bài: {selectedAssignment?.title}</div>
            <input
              type="file"
              multiple
              onChange={e => setSelectedFiles(e.target.files)}
              style={{ marginBottom: 16 }}
            />
            <div style={{ display: "flex", gap: 12 }}>
              <button
                onClick={handleSubmit}
                disabled={!selectedFiles || loading}
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