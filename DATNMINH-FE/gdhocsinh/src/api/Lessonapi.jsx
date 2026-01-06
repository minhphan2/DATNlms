const token = sessionStorage.getItem('token');

export async function getLessonBySectionId(token, sectionId){
    const response = await fetch(`http://localhost:8080/api/lessons/bysection/${sectionId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        
        },
    });
    if (!response.ok) throw new Error('Failed to fetch courses');
    return response.json();
}