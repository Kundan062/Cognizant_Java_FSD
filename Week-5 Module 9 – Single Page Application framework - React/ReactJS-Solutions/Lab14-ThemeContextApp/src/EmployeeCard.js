import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';

// Lab 14: Retrieve theme via useContext() instead of receiving it as a prop
function EmployeeCard({ employee }) {
  const theme = useContext(ThemeContext);

  return (
    <div className={`card card-${theme}`}>
      <h4>{employee.name}</h4>
      <p>{employee.designation}</p>
      <button className={`btn btn-${theme}`}>View Profile</button>
    </div>
  );
}

export default EmployeeCard;
