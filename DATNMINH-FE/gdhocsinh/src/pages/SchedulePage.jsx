import React, { useEffect, useState } from "react";
import SidebarComponent from "../components/SidebarComponent";
import Headder from "../components/Headder";
import { Icon } from "@iconify/react";
import { getScheduleByCourse } from "../api/Scheduleapi";

function SchedulePage() {
  const [schedule, setSchedule] = useState([]);
  const token = localStorage.getItem('token');
  const courseId = localStorage.getItem('courseId');
  const [loading, setLoading] = useState(true);

    useEffect(() => {
        if(!courseId) return;
        getScheduleByCourse(courseId,token)
        .then(data => setSchedule(data))
        .finally(() => setLoading(false));
    }, [courseId, token]);
  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ padding: "32px" }}>
          <h1 style={{ fontSize: 28, fontWeight: 700, marginBottom: 24 }}>
            <Icon icon="lucide:calendar" style={{ fontSize: 32, color: "#007bff", marginRight: 12 }} />
            Thời khóa biểu
          </h1>
          <div style={{
            background: "#fff",
            borderRadius: 12,
            boxShadow: "0 2px 8px #eee",
            padding: 24,
            overflowX: "auto"
          }}>
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
              <thead>
                <tr style={{ background: "#eaf3ff" }}>
                  <th style={{ padding: 12, fontWeight: 600, color: "#007bff" }}>Thứ</th>
                  <th style={{ padding: 12, fontWeight: 600, color: "#007bff" }}>Thời gian</th>
                  <th style={{ padding: 12, fontWeight: 600, color: "#007bff" }}>Môn học</th>
                  <th style={{ padding: 12, fontWeight: 600, color: "#007bff" }}>Phòng</th>
                  <th style={{ padding: 12, fontWeight: 600, color: "#007bff" }}>Giảng viên</th>
                </tr>
              </thead>
              <tbody>
                {loading ? (
                  <tr>
                    <td colSpan={5} style={{ textAlign: "center", padding: 24 }}>Đang tải...</td>
                  </tr>
                ) : schedule.length === 0 ? (
                  <tr>
                    <td colSpan={5} style={{ textAlign: "center", padding: 24 }}>Không có dữ liệu thời khóa biểu.</td>
                  </tr>
                ) : (
                  schedule.map((item, idx) => (
                    <tr key={idx} style={{ borderBottom: "1px solid #e5eaf2" }}>
                      <td style={{ padding: 12 }}>{item.day}</td>
                      <td style={{ padding: 12 }}>{item.time}</td>
                      <td style={{ padding: 12 }}>{item.subject}</td>
                      <td style={{ padding: 12 }}>{item.room}</td>
                      <td style={{ padding: 12 }}>{item.teacher}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SchedulePage;