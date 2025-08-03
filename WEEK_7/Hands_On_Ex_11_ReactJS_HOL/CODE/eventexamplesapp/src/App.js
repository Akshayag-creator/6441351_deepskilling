import React, { Component } from 'react';
import CurrencyConverter from './CurrencyConverter';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0
    };
  }

  increment = () => {
    this.setState({ count: this.state.count + 1 });
    this.sayHello();
  }

  sayHello = () => {
    alert("Hello! Value has been incremented.");
  }

  decrement = () => {
    this.setState({ count: this.state.count - 1 });
  }

  sayWelcome = (msg) => {
    alert(msg);
  }

  handleClick = (event) => {
    alert("I was clicked");
    console.log("Synthetic Event Type: ", event.type); // Example of synthetic event
  }

  render() {
    return (
      <div className="App" style={{ textAlign: "center", marginTop: "50px" }}>
        <h1>React Events Example</h1>

        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.increment}>Increment</button> &nbsp;
        <button onClick={this.decrement}>Decrement</button>

        <br /><br />

        <button onClick={() => this.sayWelcome("Welcome to React Event Handling!")}>
          Say Welcome
        </button>

        <br /><br />

        <button onClick={this.handleClick}>OnPress</button>

        <br /><br />

        <CurrencyConverter />
      </div>
    );
  }
}

export default App;
