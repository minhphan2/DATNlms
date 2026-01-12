export async function getAllDepartments() {
  const token = sessionStorage.getItem("token");
  const response = await fetch("http://localhost:8080/api/departments", {
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    }
  });
  if (!response.ok) throw new Error("Failed to fetch departments");
  return response.json();
}