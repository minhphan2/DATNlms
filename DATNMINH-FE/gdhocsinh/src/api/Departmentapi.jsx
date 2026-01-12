export async function getDepartments(token) {
    const response = await fetch(`http://localhost:8080/api/departments`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    });
    if (!response.ok) {
      throw new Error('Failed to fetch departments');
    }
    return await response.json();
}

export async function getCoursesByDepartment(departmentId, token) {
  const response = await fetch(`http://localhost:8080/api/courses/bydepartment/${departmentId}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
  });
  if (!response.ok) throw new Error('Failed to fetch courses by department');
  return response.json();
}

