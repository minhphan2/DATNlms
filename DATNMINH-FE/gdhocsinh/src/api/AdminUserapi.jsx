export async function getAllUsers() {
  const token = sessionStorage.getItem("token");
  const response = await fetch("http://localhost:8080/api/users", {
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    }
  });
  if (!response.ok) throw new Error("Failed to fetch users");
  return response.json();
}