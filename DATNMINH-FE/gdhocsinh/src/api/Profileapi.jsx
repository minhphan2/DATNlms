

export async function getProfile(token) {
    
    const response = await fetch('http://localhost:8080/api/users/me', {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch profile');
    return response.json();
}