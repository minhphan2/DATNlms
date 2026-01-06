

export async function getCourses(){
    const token = sessionStorage.getItem('token');
    const response = await fetch('http://localhost:8080/api/courses', {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        
        },
    });
    if (!response.ok) throw new Error('Failed to fetch courses');
    return response.json();
}


export async function getMyCourses(){
    const token = sessionStorage.getItem('token');
    const response = await fetch('http://localhost:8080/api/users/my-courses', {
        method: 'GET',
        headers:{
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch my courses');
    return response.json();
}

export async function getCoursesById(courseId){
    const token = sessionStorage.getItem('token');
    const response = await fetch(`http://localhost:8080/api/courses/${courseId}`, {
        method: 'GET',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        },
    });
    if (!response.ok) throw new Error('Failed to fetch course by id');
    return response.json();
}

export async function enrollCourse(userId,courseId,token){
    const response = await fetch(`http://localhost:8080/api/enrollments`, {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json' ,
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({userId,courseId}),
    });
    if (!response.ok) throw new Error('Failed to enroll course');
    return response.json();
}

export async function getMyTeacherCourses(teacherId, token) {
  const res = await fetch(`http://localhost:8080/api/courses/teacher/${teacherId}`, {
    headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Failed to fetch teacher courses");
  return res.json();
}

export async function getAssignments(courseId, token) {
  const res = await fetch(`http://localhost:8080/api/assignments?courseId=${courseId}`, {
    headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Failed to fetch assignments");
  return res.json();
}


export async function getCourseDetail(courseId, token) {
  const res = await fetch(`http://localhost:8080/api/courses/${courseId}`, {
    headers: { "Authorization": `Bearer ${token}` }
  });
  if (!res.ok) throw new Error("Failed to fetch course detail");
  return res.json();
}


