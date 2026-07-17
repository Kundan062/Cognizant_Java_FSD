import React from 'react';
import './App.css';

// Lab 7: Props example - Cart class component and OnlineShopping class component
class Cart extends React.Component {
  render() {
    return (
      <tr>
        <td>{this.props.itemname}</td>
        <td>{this.props.price}</td>
      </tr>
    );
  }
}

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.items = [
      { itemname: 'Laptop', price: 55000 },
      { itemname: 'Headphones', price: 2000 },
      { itemname: 'Mouse', price: 500 },
      { itemname: 'Keyboard', price: 800 },
      { itemname: 'Monitor', price: 12000 },
    ];
  }

  render() {
    return (
      <div>
        <h2>Online Shopping Cart</h2>
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {this.items.map((item, index) => (
              <Cart key={index} itemname={item.itemname} price={item.price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

function App() {
  return (
    <div className="App">
      <OnlineShopping />
    </div>
  );
}

export default App;
