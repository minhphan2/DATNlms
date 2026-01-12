export async function getStudentCoursesId(courseId, token){
    token = sessionStorage.getItem('token');
    const response = await fetch(`http://localhost:8080/api/users/bycourse/${courseId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch course by id');
    return response.json();
}

export async function getCurrentUser(token) {
    token = token || sessionStorage.getItem('token');
    const response = await fetch('http://localhost:8080/api/users/me', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
    if (!response.ok) throw new Error('Failed to fetch current user');
    const data = await response.json();
    sessionStorage.setItem("user", JSON.stringify(data));
    return data;
}