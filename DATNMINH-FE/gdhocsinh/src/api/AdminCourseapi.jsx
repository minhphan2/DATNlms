export async function getAllCourses() {
  const token = sessionStorage.getItem("token");
  const response = await fetch("http://localhost:8080/api/courses", {
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    }
  });
  if (!response.ok) throw new Error("Failed to fetch courses");
  return response.json();
}