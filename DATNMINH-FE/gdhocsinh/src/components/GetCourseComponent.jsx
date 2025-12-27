import React from "react";
import { useEffect, useState } from "react";
import { getCourses } from "../api/Courseapi.jsx";
import '../assets/CSS/StudentDashBoard.css';

function GetCourseComponent() {
    const [courses, setCourses] = useState([]);
    const [error, setError] = useState('');
    useEffect(() => {
        getCourses()
            .then(data => setCourses(data))
            .catch(error => setError('Failed to load courses'));
    }
    , []);

    if (error) {
        return <div style={{ color: 'red' }}>{error}</div>;
    }

    return (
        <div className="categories-grid">
            {courses.map(course => (
                <div key={course.id} className="category-card">
                    <h3 className="cat-name">{course.title}</h3>
                    <p className="course-description">{course.description}</p>
                </div>
            ))}
        </div>
    );
}

export default GetCourseComponent;