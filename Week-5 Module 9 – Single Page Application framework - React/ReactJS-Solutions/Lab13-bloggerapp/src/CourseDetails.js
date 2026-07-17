import React from 'react';

// Conditional rendering technique 3: logical && operator (element variable / short-circuit)
function CourseDetails({ seatsLeft }) {
  return (
    <div>
      <h3>Course Details</h3>
      <p>Seats left: {seatsLeft}</p>
      {seatsLeft === 0 && <p>Sorry, this course is fully booked.</p>}
      {seatsLeft > 0 && seatsLeft <= 5 && <p>Hurry! Only a few seats left.</p>}
    </div>
  );
}

export default CourseDetails;
