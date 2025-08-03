// src/App.js
import React from 'react';
import './App.css';
import { books, blogs, courses } from './data';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function App() {
  const showBooks = true;
  const showBlogs = true;
  const showCourses = true;

  return (
    <div className="App">
      <div className="container">
        {showCourses && <CourseDetails courses={courses} />}
        {showBooks ? <BookDetails books={books} /> : <p>No Books Available</p>}
        {showBlogs && <BlogDetails blogs={blogs} />}
      </div>
    </div>
  );
}

export default App;
