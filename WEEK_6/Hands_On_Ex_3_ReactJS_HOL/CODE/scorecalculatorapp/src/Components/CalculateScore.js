import React from 'react';
import '../Stylesheets/mystyle.css';  // Importing the CSS file

function CalculateScore() {
  const name = "Akshaya";
  const school = "ABC Public School";
  const total = 420;
  const goal = 500;
  const average = (total / goal) * 100;

  return (
    <div className="score-box">
      <h2>Student Score Calculator</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total Marks:</strong> {total}</p>
      <p><strong>Goal Marks:</strong> {goal}</p>
      <p><strong>Average Score:</strong> {average.toFixed(2)}%</p>
    </div>
  );
}

export default CalculateScore;
