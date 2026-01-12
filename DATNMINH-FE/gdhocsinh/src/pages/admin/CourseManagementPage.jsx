import React, { useEffect, useState } from "react";
import SidebarComponent from "../../components/SidebarComponent";
import CourseTable from "../../components/admin/CourseTable";
import { getAllCourses } from "../../api/AdminCourseapi";

function CourseManagementPage() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    getAllCourses().then(setCourses);
  }, []);

  return (
    <div style={{ display: "flex" }}>
      <SidebarComponent />
      <div style={{ flex: 1, padding: 32 }}>
        <h2>Quản lý khóa học</h2>
        <CourseTable courses={courses} />
      </div>
    </div>
  );
}

export default CourseManagementPage;