// src/CourseDetails.js
import React from 'react';

function CourseDetails({ courses }) {
  return (
    <div className="mystyle1">
      <h1>Course Details</h1>
      {courses.map(course => (
        <div key={course.id}>
          <h3>{course.cname}</h3>
          <h4>{course.cdate}</h4>
        </div>
      ))}
    </div>
  );
}

export default CourseDetails;

