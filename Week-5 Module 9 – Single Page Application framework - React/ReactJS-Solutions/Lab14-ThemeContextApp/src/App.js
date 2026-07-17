import React, { useState } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';
import employees from './EmployeeData';
import './App.css';

// Lab 14: App component provides the theme value through context
function App() {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme((prev) => (prev === 'light' ? 'dark' : 'light'));
  };

  return (
    <ThemeContext.Provider value={theme}>
      <div className={`App App-${theme}`}>
        <h1>Employee Management (Context API)</h1>
        <button onClick={toggleTheme}>Toggle Theme (current: {theme})</button>
        <EmployeesList employees={employees} />
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
