import React from 'react';
import '../Stylesheets/mystyle.css';

// Lab 3: Function component that accepts Name, School, Total and Goal
// and calculates/displays the average score of a student
function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = (total / goal).toFixed(2);

  return (
    <div className="score-card">
      <h2>Student Score Card</h2>
      <p><b>Name:</b> {name}</p>
      <p><b>School:</b> {school}</p>
      <p><b>Total Score:</b> {total}</p>
      <p><b>Number of Subjects (Goal):</b> {goal}</p>
      <p><b>Average Score:</b> {average}</p>
    </div>
  );
}

export default CalculateScore;
