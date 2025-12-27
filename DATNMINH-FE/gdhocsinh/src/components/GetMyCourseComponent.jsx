import React from "react";
import { useEffect, useState } from "react";
import { getMyCourses } from "../api/Courseapi.jsx";
import '../assets/CSS/StudentDashBoard.css';    

function GetMyCourseComponent() {
    const [courses, setCourses] = useState([]);
    const [error, setError] = useState('');
    useEffect(() => {
        getMyCourses()
            .then(data => setCourses(data))
            .catch(error => setError('Failed to load my courses'));
    }
    , []);
    if (error) {
        return <div style={{ color: 'red' }}>{error}</div>;
    }
    return (
        <div className="courses-grid">
            {courses.map(course => (
                <div key={course.id} className="course-card">
                    <span className="course-code">{course.id}</span>
                    <h3 className="course-title">{course.title}</h3>
                    <p className="course-meta">{course.description}</p>
                </div>
            ))}
        </div>
    );
}

export default GetMyCourseComponent;