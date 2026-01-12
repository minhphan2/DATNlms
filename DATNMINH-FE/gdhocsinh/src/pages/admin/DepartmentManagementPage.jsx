import React, { useEffect, useState } from "react";
import DepartmentTable from "../../components/admin/DepartmentTable";
import { getAllDepartments } from "../../api/AdminDepartmentApi";
import SidebarComponent from "../../components/SidebarComponent";

function DepartmentManagementPage() {
  const [departments, setDepartments] = useState([]);

  useEffect(() => {
    getAllDepartments().then(setDepartments);
  }, []);

  return (
    <div style={{ display: "flex" }}>
      <SidebarComponent />
      <div style={{ flex: 1, padding: 32 }}>
        <h2>Quản lý phòng ban</h2>
        <DepartmentTable departments={departments} />
      </div>
    </div>
  );
}

export default DepartmentManagementPage;