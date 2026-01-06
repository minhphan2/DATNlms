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