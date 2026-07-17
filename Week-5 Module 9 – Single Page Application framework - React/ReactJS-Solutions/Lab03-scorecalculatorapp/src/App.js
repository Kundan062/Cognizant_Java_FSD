import React from 'react';
import CalculateScore from './Components/CalculateScore';
import './App.css';

function App() {
  return (
    <div className="App">
      <CalculateScore name="Ravi Kumar" school="City Public School" total={450} goal={5} />
    </div>
  );
}

export default App;
