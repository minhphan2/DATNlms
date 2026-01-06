export async function getMaterialsByLessonId(token, lessonId){
    const response = await fetch(`http://localhost:8080/api/materials/bylesson/${lessonId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        
        },
    });
    if (!response.ok) throw new Error('Failed to fetch courses');
    return response.json();
}

export async function downloadMaterial(token, materialId){
    const response = await fetch(`http://localhost:8080/api/materials/download/${materialId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to download material');
    return response.blob();
}

export async function uploadMaterial(token,formData){
    const response = await fetch(`http://localhost:8080/api/materials/upload`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`
        },
        body: formData
    });
    if (!response.ok) throw new Error('Failed to upload material');
    return response.json();
}