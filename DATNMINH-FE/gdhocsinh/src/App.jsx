import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import StudentLoginPage from "./pages/StudentLoginPage";
import StudentDashBoard from "./pages/StudentDashBoard";
import CourseDetail from "./pages/CourseDetail";
import ProtectedRoute from "./ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Navigate to="/login" replace />} />

        <Route path="/login" element={<StudentLoginPage />} />

        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <StudentDashBoard />
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

      </Routes>
    </BrowserRouter>
  );
}

export default App;