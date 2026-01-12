import React from "react";

function DepartmentTable({ departments }) {
  return (
    <table border="1" cellPadding={8} style={{ width: "100%", background: "#fff" }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Tên phòng ban</th>
        </tr>
      </thead>
      <tbody>
        {departments.map(d => (
          <tr key={d.id}>
            <td>{d.id}</td>
            <td>{d.name}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default DepartmentTable;