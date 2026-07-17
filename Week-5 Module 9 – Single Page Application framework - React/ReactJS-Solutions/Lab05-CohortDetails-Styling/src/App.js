import React from 'react';
import CohortDetails from './CohortDetails';
import cohorts from './CohortData';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>Cognizant Academy - Cohort Dashboard</h1>
      {cohorts.map((cohort) => (
        <CohortDetails key={cohort.code} cohort={cohort} />
      ))}
    </div>
  );
}

export default App;
