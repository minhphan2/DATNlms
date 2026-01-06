import React, { useState } from "react";
import { uploadMaterial } from "../api/Materialsapi";

function MaterialUploadForm({ lessonId, onUploaded }) {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) return;
    setLoading(true);
    const token = sessionStorage.getItem("token");
    const formData = new FormData();
    formData.append("lessonId", lessonId);
    formData.append("file", file);
    formData.append("fileType", file.type);

    try {
      const result = await uploadMaterial(token, formData);
      setFile(null);
      if (onUploaded) onUploaded(result);
      alert("Tải lên thành công!");
    } catch (err) {
      alert("Tải lên thất bại!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ margin: "8px 0 16px 32px" }}>
      <input
        type="file"
        onChange={e => setFile(e.target.files[0])}
        required
        style={{ marginRight: 8 }}
      />
      <button
        type="submit"
        disabled={loading}
        style={{
          background: "#007bff",
          color: "#fff",
          border: "none",
          borderRadius: 6,
          padding: "6px 16px",
          fontWeight: 600,
          cursor: "pointer"
        }}
      >
        {loading ? "Đang tải..." : "Tải lên tài liệu"}
      </button>
    </form>
  );
}

export default MaterialUploadForm;