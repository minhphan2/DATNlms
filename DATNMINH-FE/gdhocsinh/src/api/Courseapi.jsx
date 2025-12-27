export async function getCourses(){
    const response = await fetch('http://localhost:8080/api/courses', {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        
        },
    });
    if (!response.ok) throw new Error('Failed to fetch courses');
    return response.json();
}


export async function getMyCourses(){
    const response = await fetch('http://localhost:8080/api/users/my-courses', {
        method: 'GET',
        headers:{
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch my courses');
    return response.json();
}