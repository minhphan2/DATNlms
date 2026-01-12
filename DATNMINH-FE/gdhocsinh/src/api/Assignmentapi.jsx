// Tạo assignment (chỉ gửi JSON, không gửi file)
export async function createAssignment(data, token) {
  const response = await fetch("http://localhost:8080/api/assignments", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
    body: JSON.stringify(data)
  });
  if (!response.ok) throw new Error("Failed to create assignment");
  return response.json();
}

// Upload nhiều file đính kèm cho assignment (bảng assignment_attachment)
export async function uploadAssignmentAttachments(token, assignmentId, files) {
  const formData = new FormData();
  files.forEach(file => formData.append("files", file));
  const response = await fetch(
    `http://localhost:8080/api/assignments/${assignmentId}/attachments`,
    {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: formData,
    }
  );
  if (!response.ok) throw new Error("Upload failed");
  return await response.json(); // trả về danh sách file đính kèm
}

// Lấy danh sách file đính kèm của assignment
export async function getAssignmentAttachments(token, assignmentId) {
  const response = await fetch(
    `http://localhost:8080/api/assignments/${assignmentId}/attachments`,
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
  if (!response.ok) throw new Error("Get attachments failed");
  return await response.json();
}

// Download file đính kèm theo attachmentId
export async function downloadAssignmentAttachment(token, attachmentId) {
  const response = await fetch(
    `http://localhost:8080/api/assignments/attachments/download/${attachmentId}`,
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
  if (!response.ok) throw new Error("Download failed");
  return await response.blob();
}

// Hàm tổng hợp: tạo assignment và upload nhiều file đính kèm (nếu có)
export async function createAssignmentWithFiles(data, files, token) {
  const assignment = await createAssignment(data, token);
  if (files && files.length > 0) {
    await uploadAssignmentAttachments(token, assignment.assignmentId, files);
  }
  return assignment;
}


export async function getAssignmentsByCourse(token, courseId) {
  const res = await fetch(`http://localhost:8080/api/assignments?courseId=${courseId}`, {
    headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Failed to fetch assignments");
  return res.json();
}


export async function getSubmissionByAssignmentAndStudent(token, assignmentId, studentId) {
  const res = await fetch(
    `http://localhost:8080/api/submissions/by-assignment-and-student?assignmentId=${assignmentId}&studentId=${studentId}`,
    {
      headers: { "Authorization": `Bearer ${token}` }
    }
  );
  if (!res.ok) throw new Error("Failed to fetch submission");
  // Nếu trả về chuỗi, parse lại
  const text = await res.text();
  try {
    const data = JSON.parse(text);
    // Nếu có trường message thì chưa nộp
    if (data.message) return null;
    return data;
  } catch {
    // Nếu không parse được thì chưa nộp
    return null;
  }

}



export async function getSubmissionCount(token, assignmentId) {
  const res = await fetch(
    `http://localhost:8080/api/submissions/count-by-assignment?assignmentId=${assignmentId}`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  if (!res.ok) throw new Error("Failed to fetch submission count");
  const data = await res.json();
  return typeof data === "number" ? data : data.count || 0;
}


export async function getStudentCount(token, courseId) {
  const res = await fetch(
    `http://localhost:8080/api/enrollments/student-count?courseId=${courseId}`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  if (!res.ok) throw new Error("Failed to fetch student count");
  const data = await res.json();
  // Nếu API trả về số nguyên, dùng luôn:
  return typeof data === "number" ? data : data.count || 0;
}








export async function downloadSubmissionAttachment(token, attachmentId) {
  const res = await fetch(
    `http://localhost:8080/api/submissions/attachments/download/${attachmentId}`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  if (!res.ok) throw new Error("Failed to download submission attachment");
  return await res.blob();
}


export async function getAssignmentDetail(token, assignmentId) {
  const res = await fetch(
    `http://localhost:8080/api/assignments/${assignmentId}`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  if (!res.ok) throw new Error("Failed to fetch assignment detail");
  return res.json();
}

export async function getAssignmentSubmissions(token, assignmentId) {
  const res = await fetch(
    `http://localhost:8080/api/submissions/byassignment/${assignmentId}`,
    { headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Failed to fetch assignment submissions");
  return await res.json();
}

export async function getStudentName(token, studentId) {
  const res = await fetch(
    `http://localhost:8080/api/users/student/${studentId}`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  if (!res.ok) return "Unknown";
  const data = await res.json();
  return data.fullname || "Unknown";
}