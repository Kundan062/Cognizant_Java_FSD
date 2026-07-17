import React from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';
import './App.css';

// Lab 13: Demonstrates multiple ways of conditional rendering
function App() {
  return (
    <div className="App">
      <h1>Blogger App</h1>
      <BookDetails available={true} />
      <BlogDetails published={false} />
      <CourseDetails seatsLeft={3} />
    </div>
  );
}

export default App;
