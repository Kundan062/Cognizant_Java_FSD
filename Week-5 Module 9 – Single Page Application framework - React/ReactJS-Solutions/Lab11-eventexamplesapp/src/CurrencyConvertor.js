import React, { useState } from 'react';

// Lab 11: CurrencyConvertor component - converts INR to EUR on button click
function CurrencyConvertor() {
  const [rupees, setRupees] = useState('');
  const [euro, setEuro] = useState(null);

  const RATE = 0.011; // approx INR -> EUR conversion rate

  const handleSubmit = (event) => {
    event.preventDefault();
    const converted = (parseFloat(rupees) || 0) * RATE;
    setEuro(converted.toFixed(2));
  };

  return (
    <div>
      <h2>Currency Convertor (INR to EUR)</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Enter amount in Rupees"
          value={rupees}
          onChange={(e) => setRupees(e.target.value)}
        />
        <button type="submit">Convert</button>
      </form>
      {euro !== null && <p>{rupees} INR = {euro} EUR</p>}
    </div>
  );
}

export default CurrencyConvertor;
