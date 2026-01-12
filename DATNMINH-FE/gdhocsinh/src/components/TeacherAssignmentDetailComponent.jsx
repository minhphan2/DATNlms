import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAssignmentDetail, getAssignmentSubmissions, getStudentName } from "../api/Assignmentapi";
import { getSubmissionAttachments } from "../api/Submissionapi";
import { Icon } from "@iconify/react";

function TeacherAssignmentDetail() {
  const { assignmentId } = useParams();
  const [assignment, setAssignment] = useState(null);
  const [submissions, setSubmissions] = useState([]);
  const [studentNames, setStudentNames] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      const token = sessionStorage.getItem("token");
      const detail = await getAssignmentDetail(token, assignmentId);
      setAssignment(detail);
      const subs = await getAssignmentSubmissions(token, assignmentId);

      // Lấy attachments cho từng submission
      for (const sub of subs) {
        const attachments = await getSubmissionAttachments(token, sub.submissionId);
        sub.attachments = attachments; // mỗi attachment có fileName, fileUrl
      }
      setSubmissions(subs);

      // Lấy tên học sinh cho từng studentId
      const names = {};
      for (const sub of subs) {
        if (!names[sub.studentId]) {
          names[sub.studentId] = await getStudentName(token, sub.studentId);
        }
      }
      setStudentNames(names);
    };
    fetchData();
  }, [assignmentId]);

  if (!assignment) return <div>Đang tải...</div>;

  return (
    <div style={{ width:"100%", margin: "0 auto", background: "#fff", borderRadius: 12, boxShadow: "0 2px 8px #eee", padding: 24 }}>
      <h2>{assignment.title}</h2>
      <div>Hạn cuối: {assignment.deadline}</div>
      <div>Hướng dẫn: {assignment.instructions}</div>
      <div>Điểm tối đa: {assignment.maxScore}</div>
      <hr />
      <h3>Danh sách bài nộp</h3>
      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr>
            <th>Học sinh</th>
            <th>Thời gian nộp</th>
            <th>File</th>
            <th>Điểm</th>
          </tr>
        </thead>
        <tbody>
          {submissions.map(sub => (
            <tr key={sub.submissionId}>
              <td style={{textAlign: "center"}}>{studentNames[sub.studentId] || sub.studentId}</td>
              <td style={{textAlign: "center"}}>{sub.submittedAt || "-"}</td>
              <td style={{ display: "grid", gap: 8, textAlign: "center" }}>
                {sub.attachments && sub.attachments.length > 0 ? (
                  sub.attachments.map((att, idx) => (
                    <a
                      key={idx}
                      href={att.fileUrl}
                      style={{ color: "#007bff", marginRight: 8 }}
                      target="_blank"
                      rel="noopener noreferrer"
                    >
                      <Icon icon="lucide:paperclip" /> {att.fileName}
                    </a>
                  ))
                ) : (
                  "-"
                )}
              </td>
              <td>{sub.score ?? "-"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TeacherAssignmentDetail;