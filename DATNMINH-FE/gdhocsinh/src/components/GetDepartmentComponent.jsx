import React from "react";
import "../assets/CSS/StudentDashBoard.css";
import { getDepartments } from "../api/Departmentapi";

function GetDepartmentComponent({ departments, loading }) {

    
  if (loading) return <div>Đang tải danh sách khoa...</div>;
    if (!departments || departments.length === 0) {
        return <div>Không có khoa nào.</div>;
    }
    return (
        <div className="courses-grid">
            {departments.map(department => (
                <div key={department.id} className="course-card">
                    <h3 className="department-name">{department.name}</h3>
                </div>
            ))}
        </div>
    );
}

export default GetDepartmentComponent;
