import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import StudentLoginPage from "./pages/StudentLoginPage";
import StudentDashBoard from "./pages/student/StudentDashBoard";
import CourseDetail from "./pages/student/CourseDetail";
import ProtectedRoute from "./ProtectedRoute";
import SectionDetail from "./pages/student/SectionDetail";
import MyCourse from "./pages/student/MyCourse";
import SchedulePage from "./pages/student/SchedulePage";
import TeacherDashBoard from "./pages/teacher/TeacherDashBoard";
import TeacherCourseDetail from "./pages/teacher/TeacherCourseDetail";
import TeacherMyCourse from "./pages/teacher/TeacherMyCourse"

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Navigate to="/login" replace />} />

        <Route path="/login" element={<StudentLoginPage />} />

        <Route
          path="/student/dashboard"
          element={
            <ProtectedRoute>
              <StudentDashBoard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/teacher/dashboard"
          element={
            <ProtectedRoute>
              <TeacherDashBoard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/teacher/course/:courseId"
          element={
            <ProtectedRoute>
              <TeacherCourseDetail />
            </ProtectedRoute>
          }
        />

        <Route
          path="/courses/:courseId"
          element={
            <ProtectedRoute>
              <CourseDetail />
            </ProtectedRoute>
          }
        />

        <Route
          path="/section/:sectionId"
          element={
            <ProtectedRoute>
              <SectionDetail />
            </ProtectedRoute>
          }
        />



        <Route
          path="/teacher/my-courses"
          element={
            <ProtectedRoute>
              <TeacherMyCourse/>
            </ProtectedRoute>
          }
        />
        

        <Route
          path="/student/my-courses"
          element={
            <ProtectedRoute>
              <MyCourse />
            </ProtectedRoute>
          }
        />

        <Route
          path="schedule"
          element={
            <ProtectedRoute>
              <SchedulePage />
            </ProtectedRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;