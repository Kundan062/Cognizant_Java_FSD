import React from 'react';

// Lab 8: React State - track entrycount and exitcount in component state
class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }

  updateEntry() {
    this.setState((prevState) => ({ entrycount: prevState.entrycount + 1 }));
  }

  updateExit() {
    this.setState((prevState) => ({ exitcount: prevState.exitcount + 1 }));
  }

  render() {
    return (
      <div>
        <h2>Mall Entry/Exit Counter</h2>
        <button onClick={this.updateEntry}>Login</button>
        <button onClick={this.updateExit}>Exit</button>
        <p>Number of people entered the mall: {this.state.entrycount}</p>
        <p>Number of people exited the mall: {this.state.exitcount}</p>
      </div>
    );
  }
}

export default CountPeople;
