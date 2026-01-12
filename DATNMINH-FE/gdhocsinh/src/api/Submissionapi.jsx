export async function uploadSubmissionAttachment(token, submissionId, files) {
  const formData = new FormData();
  for (let i = 0; i < files.length; i++) {
    formData.append("files", files[i]);
  }
  const res = await fetch(`http://localhost:8080/api/submissions/attachments/${submissionId}`, {
    method: "POST",
    headers: { "Authorization": `Bearer ${token}` },
    body: formData
  });
  if (!res.ok) throw new Error("Upload thất bại!");
  return await res.json();
}

export async function getSubmissionAttachments(token, submissionId) {
  const res = await fetch(`http://localhost:8080/api/submissions/attachments/${submissionId}`, {
    headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Lấy file thất bại!");
  return await res.json();
}

export async function submitAssignment(token, formData) {
  const res = await fetch("http://localhost:8080/api/submissions/submit", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${token}`
    },
    body: formData
  });
  if (!res.ok) throw new Error("Nộp bài thất bại!");
  return await res.json();
}

export async function createSubmissionWithFiles(formData, files, token) {
  const submission = await submitAssignment(token, formData);
    if (files && files.length > 0) {
        await uploadSubmissionAttachment(token, submission.submissionId, files);
    }
    return submission;
}