import React from 'react';

// Guest page - flight details visible, booking not allowed
function Guest() {
  const flights = [
    { flightNo: 'AI-202', from: 'Delhi', to: 'Mumbai', time: '08:30 AM' },
    { flightNo: 'AI-505', from: 'Bengaluru', to: 'Hyderabad', time: '11:15 AM' },
  ];

  return (
    <div>
      <h2>Available Flights (Guest View)</h2>
      <ul>
        {flights.map((f) => (
          <li key={f.flightNo}>
            {f.flightNo}: {f.from} -&gt; {f.to} at {f.time}
          </li>
        ))}
      </ul>
      <p>Please login to book tickets.</p>
    </div>
  );
}

export default Guest;
