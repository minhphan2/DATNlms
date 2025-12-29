export async function getSection(token, courseId) {
    const response = await fetch(`http://localhost:8080/api/sections/bycourse/${courseId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch section');
    return response.json();
}