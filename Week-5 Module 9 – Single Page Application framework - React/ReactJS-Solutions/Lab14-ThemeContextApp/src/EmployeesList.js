import React from 'react';
import EmployeeCard from './EmployeeCard';

// Lab 14: No longer passes the theme prop explicitly to EmployeeCard
function EmployeesList({ employees }) {
  return (
    <div>
      <h2>Employees List</h2>
      {employees.map((emp) => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
}

export default EmployeesList;
