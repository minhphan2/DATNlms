import React, { useEffect, useState } from "react";
import SidebarComponent from "../../components/SidebarComponent";
import { Icon } from "@iconify/react";
import { getScheduleByStudent } from "../../api/Scheduleapi";


// Lấy ngày thứ 2 của tuần hiện tại
function getMonday(date) {
  const d = new Date(date);
  const day = d.getDay();
  const diff = d.getDate() - day + (day === 0 ? -6 : 1);
  d.setDate(diff);
  d.setHours(0,0,0,0);
  return d;
}

// Cộng ngày
function addDays(date, days) {
  const d = new Date(date);
  d.setDate(d.getDate() + days);
  return d;
}

// Định dạng ngày YYYY-MM-DD
function formatDate(date) {
  return date.toISOString().slice(0, 10);
}

const weekDaysVN = ["Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "CN"];

function SchedulePage() {
  const [schedule, setSchedule] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentMonday, setCurrentMonday] = useState(getMonday(new Date()));

  const token = sessionStorage.getItem('token');
  const userStr = sessionStorage.getItem('user');
  const user = userStr ? JSON.parse(userStr) : null;
  const studentId = user ? user.id : null;

  useEffect(() => {
    if (!studentId) return;
    setLoading(true);
    getScheduleByStudent(token, studentId)
      .then(data => {
        setSchedule(data);
      })
      .catch(err => {
        setSchedule([]);
        console.error("Schedule API error:", err);
      })
      .finally(() => setLoading(false));
  }, [token, studentId]);

  // Tính các ngày trong tuần hiện tại
  const weekDates = Array.from({ length: 7 }, (_, i) => addDays(currentMonday, i));
  // Lọc lịch học theo tuần hiện tại
  const scheduleByDay = weekDates.map(day => {
    const dayStr = formatDate(day);
    return schedule.filter(item => item.startDate && item.startDate.slice(0, 10) === dayStr);
  });

  return (
    <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
      <SidebarComponent />
      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ padding: "32px" }}>
          <h1 style={{ fontSize: 28, fontWeight: 700, marginBottom: 24 }}>
            <Icon icon="lucide:calendar" style={{ fontSize: 32, color: "#007bff", marginRight: 12 }} />
            Thời khóa biểu
          </h1>
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 16 }}>
            <button onClick={() => setCurrentMonday(addDays(currentMonday, -7))}>← Tuần trước</button>
            <span>
              Tuần: {formatDate(weekDates[0])} - {formatDate(weekDates[6])}
            </span>
            <button onClick={() => setCurrentMonday(addDays(currentMonday, 7))}>Tuần sau →</button>
          </div>
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
                  {weekDaysVN.map((thu, idx) => (
                    <th key={idx} style={{ padding: 12, fontWeight: 600, color: "#007bff", border: "1px solid #000308ff",
    borderRight: "1px solid #01050cff"}}>{thu}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                <tr>
                  {scheduleByDay.map((lessons, idx) => (
                    <td key={idx} style={{ verticalAlign: "top", minWidth: 180,  border: "1px solid #000308ff",
    borderRight: "1px solid #000204ff"}}>
                      {loading ? (
                        <div>Đang tải...</div>
                      ) : lessons.length === 0 ? (
                        <div style={{ color: "#100606ff", textAlign:"center"}}>Không có</div>
                      ) : (
                        lessons.map((item, i) => (
                          <div key={i} style={{ marginBottom: 8, border: "1px solid #eee", padding: 8, borderRadius: 6 }}>
                            <div><b>{item.courseName}</b></div>
                            <div>{item.startTime} - {item.endTime}</div>
                            <div>Phòng: {item.location}</div>
                            <div>GV: {item.teacherName}</div>
                          </div>
                        ))
                      )}
                    </td>
                  ))}
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SchedulePage;