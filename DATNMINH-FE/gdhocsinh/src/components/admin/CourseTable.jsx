import React from "react";

function CourseTable({ courses }) {
  return (
    <table border="1" cellPadding={8} style={{ width: "100%", background: "#fff" }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên khóa học</th>
          <th>Phòng ban</th>
          <th>Giáo viên</th>
          <th>Ngày bắt đầu</th>
          <th>Ngày kết thúc</th>
          <th>Trạng thái</th>
        </tr>
      </thead>
      <tbody>
        {courses.map(c => (
            console.log(c),
          <tr key={c.id}>
            <td>{c.id}</td>
            <td>{c.coursename || c.title}</td>
            <td>{c.department?.name}</td>
            <td>{c.teacherName}</td>
            <td>{c.startdate}</td>
            <td>{c.enddate}</td>
            <td>{c.status}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default CourseTable;