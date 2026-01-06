import React, { useEffect, useState } from "react";
import { getStudentCoursesId } from "../api/Userapi";

const ListStudent = ({ courseId, token }) => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!courseId || !token) return;
    setLoading(true);
    getStudentCoursesId(courseId, token)
      .then((data) =>
        console.log(data) ||
         setStudents(data))
      .catch(() => setStudents([]))
      .finally(() => setLoading(false));
  }, [courseId, token]);

  if (loading) return <div>Đang tải danh sách học viên...</div>;
  if (!students || students.length === 0) {
    return <div>Không có học viên nào.</div>;
  }
  return (
    <div>
      <h3 style={{ marginBottom: 16 }}>Danh sách học viên</h3>
      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr style={{ background: "#eaf3ff" }}>
            <th style={{ padding: 8, border: "1px solid #000308ff", width:"15%" }}>Mã Sinh viên</th>
            <th style={{ padding: 8, border: "1px solid #000308ff", width:"35%" }}>Họ Và Tên</th>
            <th style={{ padding: 8, border: "1px solid #000308ff", width:"35%" }}>Email</th>
            <th style={{ padding: 8, border: "1px solid #000308ff", width:"15%" }}>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          {students.map((student, idx) => (
            <tr key={student.id || idx} style={{ borderBottom: "1px solid #eee" }}>
              <td style={{ padding: 8, border: "1px solid #000308ff", width:"15%", textAlign:"center"}}>{student.id}</td>
              <td style={{ padding: 8,  border: "1px solid #000308ff", width:"35%", textAlign:"center" }}>{student.fullname}</td>
              <td style={{ padding: 8,  border: "1px solid #000308ff", width:"35%", textAlign:"center" }}>{student.email}</td>
              <td style={{ padding: 8,  border: "1px solid #000308ff", width:"15%", textAlign:"center" }}>{student.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListStudent;