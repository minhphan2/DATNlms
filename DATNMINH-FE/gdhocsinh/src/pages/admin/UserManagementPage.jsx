import React, { useEffect, useState } from "react";
import SidebarComponent from "../../components/SidebarComponent";
import UserTable from "../../components/admin/UserTable";
import { getAllUsers } from "../../api/AdminUserapi";

function UserManagementPage() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getAllUsers().then(setUsers);
  }, []);

  return (
    <div style={{ display: "flex" }}>
      <SidebarComponent />
      <div style={{ flex: 1, padding: 32 }}>
        <h2>Quản lý người dùng</h2>
        <UserTable users={users} />
      </div>
    </div>
  );
}

export default UserManagementPage;