import React from 'react';
import './App.css';

// Lab 10: JSX - elements, attributes, expressions, inline CSS
const officeSpaces = [
  { name: 'Skyline Business Center', rent: 75000, address: 'MG Road, Bengaluru' },
  { name: 'Riverside Co-working Hub', rent: 45000, address: 'Banjara Hills, Hyderabad' },
  { name: 'Metro Office Suites', rent: 58000, address: 'Andheri, Mumbai' },
];

function App() {
  const office = officeSpaces[0];

  return (
    <div className="App">
      {/* Element to display the heading of the page */}
      <h1>Office Space Rental</h1>

      {/* Attribute usage - image of the office space */}
      <img
        src="https://via.placeholder.com/300x150.png?text=Office+Space"
        alt="office space"
        width="300"
      />

      {/* Object with JavaScript expressions embedded in JSX */}
      <h2>Featured Office</h2>
      <p>Name: {office.name}</p>
      <p>Address: {office.address}</p>
      <p style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
        Rent: {office.rent}
      </p>

      {/* List of objects rendered via map() */}
      <h2>All Available Offices</h2>
      <ul>
        {officeSpaces.map((item, index) => (
          <li key={index}>
            {item.name} - {item.address} -{' '}
            <span style={{ color: item.rent < 60000 ? 'red' : 'green' }}>
              Rent: {item.rent}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
