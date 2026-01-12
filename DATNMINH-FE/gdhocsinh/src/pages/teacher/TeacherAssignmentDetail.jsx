import React from "react";
import SidebarComponent from "../../components/SidebarComponent";
import TeacherAssignmentDetail from "../../components/TeacherAssignmentDetailComponent";
import { Icon } from "@iconify/react";

function TeacherAssignmentDetailPage() {
  return (
    <>
      <div style={{ display: "flex", minHeight: "100vh", background: "#f6faff" }}>
        <SidebarComponent />
        <div style={{ flex: 1, display: "flex", justifyContent: "center", alignItems: "flex-start" }}>

            <TeacherAssignmentDetail />
        </div>
       
      </div>
    </>
  );
}

export default TeacherAssignmentDetailPage;