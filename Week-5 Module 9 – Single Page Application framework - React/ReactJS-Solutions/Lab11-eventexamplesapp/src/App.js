import React, { useState } from 'react';
import CurrencyConvertor from './CurrencyConvertor';
import './App.css';

function App() {
  const [count, setCount] = useState(0);

  // 1a. Increment count, 1b. say hello - both invoked from Increment button
  const sayHello = () => {
    console.log('Hello, this is a static message!');
  };

  const handleIncrement = () => {
    setCount(count + 1);
    sayHello();
  };

  const handleDecrement = () => {
    setCount(count - 1);
  };

  // 2. Function that takes "welcome" as argument
  const sayWelcome = (message) => {
    alert(message);
  };

  // 3. Synthetic event handler - onPress equivalent using onClick
  const onPress = (event) => {
    alert('I was clicked');
  };

  return (
    <div className="App">
      <h2>Event Handling Examples</h2>

      <p>Counter: {count}</p>
      <button onClick={handleIncrement}>Increment</button>
      <button onClick={handleDecrement}>Decrement</button>

      <br /><br />
      <button onClick={() => sayWelcome('welcome')}>Say Welcome</button>

      <br /><br />
      <button onClick={onPress}>Press Me</button>

      <hr />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
