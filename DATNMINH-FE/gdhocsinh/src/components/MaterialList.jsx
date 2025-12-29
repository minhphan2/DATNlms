import React, { useEffect, useState } from "react";
import { Icon } from "@iconify/react";
import { getMaterialsByLessonId, downloadMaterial } from "../api/Materialsapi.jsx";


function MaterialList({ lessonId }) {
  const [materials, setMaterials] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('token');
    getMaterialsByLessonId(token, lessonId)
      .then(setMaterials)
      .finally(() => setLoading(false));
  }, [lessonId]);

  // Hàm xử lý tải file
  const handleDownload = async (materialId, fileName) => {
    const token = localStorage.getItem('token');
    try {
      const blob = await downloadMaterial(token, materialId);
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      a.remove();
      window.URL.revokeObjectURL(url);
    } catch (e) {
      alert("Tải file thất bại!");
    }
  };

  return (
    <div className="material-list" style={{
      margin: "16px 0 32px 64px",
      background: "#f8faff",
      borderRadius: 8,
      padding: 16,
      minHeight: 40
    }}>
      {loading ? (
        <div>Đang tải...</div>
      ) : materials.length > 0 ? (
        materials.map((m, idx) => (
  <div key={m.id || m.fileName || idx} style={{ display: "flex", alignItems: "center", gap: 8 }}>
    <a href={m.fileUrl} target="_blank" rel="noopener noreferrer">
      {m.fileName}
    </a>
    <span
      style={{ color: "#007bff", cursor: "pointer" }}
      title="Tải xuống"
      onClick={() => handleDownload(m.materialId||m.id, m.fileName)}
    >
      <Icon icon="lucide:download" style={{ fontSize: 20, verticalAlign: "middle" }} />
    </span>
  </div>
))) : (
        <div>Không có tài liệu</div>
      )}
    </div>
  );
}

export default MaterialList;