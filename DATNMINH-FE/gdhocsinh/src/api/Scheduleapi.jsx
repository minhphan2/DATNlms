export async function getScheduleByCourse(token,courseId) {
    const response = await fetch(`http://localhost:8080/api/schedules/${courseId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch schedule');
    return response.json();
}


export async function getScheduleByStudent(token,studentId) {
    const response = await fetch(`http://localhost:8080/api/schedules/byuser/${studentId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch schedule');
    return response.json();
}