import React from "react";

function UserTable({ users }) {
  return (
    <table border="1" cellPadding={8} style={{ width: "100%", background: "#fff" }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Họ tên</th>
          <th>Email</th>
          <th>Vai trò</th>
          <th>Phòng ban</th>
          <th>Trạng thái</th>
        </tr>
      </thead>
      <tbody>
        {users.map(u => (
          <tr key={u.id}>
            <td>{u.id}</td>
            <td>{u.fullname}</td>
            <td>{u.email}</td>
            <td>{u.role}</td>
            <td>{u.department?.name}</td>
            <td>{u.status}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default UserTable;