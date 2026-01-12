import React, { useEffect, useState } from "react";
import { getCoursesByDepartment, getMyCourses } from "../../api/Courseapi.jsx";
import SidebarComponent from "../../components/SidebarComponent.jsx";
import GetCourseComponent from "../../components/GetCourseComponent.jsx";
import GetMyCourseComponent from "../../components/GetMyCourseComponent.jsx";
import { getCurrentUser } from "../../api/Userapi";

function RegisterCoursePage() {
  const [allCourses, setAllCourses] = useState([]);
  const [myCourses, setMyCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [user, setUser] = useState(null);

  const token = sessionStorage.getItem("token");

  useEffect(() => {
    async function fetchUser() {
      if (!token) return;
      let userStr = sessionStorage.getItem("user");
      let userObj = userStr ? JSON.parse(userStr) : null;
      if (!userObj || !userObj.department) {
        userObj = await getCurrentUser(token);
      }
      setUser(userObj);
    }
    fetchUser();
  }, [token]);

  const studentDepartmentId = user?.department?.id || null;
  const studentId = user?.id || null;

  useEffect(() => {
    if (!token || !studentDepartmentId || !studentId) return;
    setLoading(true);
    Promise.all([
      getCoursesByDepartment(studentDepartmentId, token),
      getMyCourses(studentId, token)
    ])
      .then(([coursesData, myCoursesData]) => {
        setAllCourses(coursesData);
        setMyCourses(myCoursesData);
      })
      .finally(() => setLoading(false));
    // eslint-disable-next-line
  }, [token, studentDepartmentId, studentId]);

  // Lọc ra các khóa học chưa đăng ký
  const myCourseIds = new Set(myCourses.map(c => c.id));
  const availableCourses = allCourses.filter(c => !myCourseIds.has(c.id));

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, padding: 32 }}>
        <h2>Đăng ký môn học khoa {user?.department?.name}</h2>
        <h3>Khóa học có thể đăng ký</h3>
        <GetCourseComponent courses={availableCourses} loading={loading} userId={user?.id} />
        <h3 style={{ marginTop: 32 }}>Khóa học đã đăng ký</h3>
        <GetMyCourseComponent courses={myCourses} loading={loading} />
      </div>
    </div>
  );
}

export default RegisterCoursePage;