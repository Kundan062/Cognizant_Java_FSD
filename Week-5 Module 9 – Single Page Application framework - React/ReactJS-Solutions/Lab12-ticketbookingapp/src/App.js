import React, { useState } from 'react';
import Guest from './Guest';
import User from './User';
import './App.css';

// Lab 12: Conditional rendering based on login state
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => setIsLoggedIn(true);
  const handleLogout = () => setIsLoggedIn(false);

  let userComponent;
  if (isLoggedIn) {
    userComponent = <User />;
  } else {
    userComponent = <Guest />;
  }

  return (
    <div className="App">
      <h1>Ticket Booking App</h1>
      {isLoggedIn ? (
        <button onClick={handleLogout}>Logout</button>
      ) : (
        <button onClick={handleLogin}>Login</button>
      )}
      {userComponent}
    </div>
  );
}

export default App;
