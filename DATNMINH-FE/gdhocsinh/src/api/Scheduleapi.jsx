export async function getScheduleByCourse(token,courseId) {
    const response = await fetch(`http://localhost:8080/api/schedule/${courseId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch schedule');
    return response.json();
}