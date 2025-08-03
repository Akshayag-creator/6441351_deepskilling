import React, { Component } from 'react';

class CurrencyConverter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: '',
      euro: null
    };
  }

  handleChange = (e) => {
    this.setState({ rupees: e.target.value });
  }

  handleSubmit = (e) => {
    e.preventDefault();
    const conversionRate = 0.011; // 1 INR = 0.011 Euro (example rate)
    const euroValue = this.state.rupees * conversionRate;
    this.setState({ euro: euroValue.toFixed(2) });
  }

  render() {
    return (
      <div style={{ marginTop: "40px" }}>
        <h2>Currency Converter (INR ➜ EUR)</h2>
        <form onSubmit={this.handleSubmit}>
          <input
            type="number"
            value={this.state.rupees}
            onChange={this.handleChange}
            placeholder="Enter amount in ₹"
          />
          &nbsp;
          <button type="submit">Convert</button>
        </form>
        {this.state.euro !== null && (
          <h3>Converted Amount: € {this.state.euro}</h3>
        )}
      </div>
    );
  }
}

export default CurrencyConverter;
