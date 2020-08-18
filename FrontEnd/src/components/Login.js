import React, { Component } from "react";
import "./Login.css";


class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    console.log(this.state);
    alert("Submitted");
  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
    event.preventDefault();
  }

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit} method="POST">
          <header className="Login-header">AGME LOGIN</header>

          <div className="form">
            

            <div className="form-input">
              <label>Username:</label>
              <input
                type="text"
                name="username"
                placeholder="username"
                value={this.state.username}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-input">
              <label>Password:</label>
              <input
                type="password"
                name="password"
                placeholder="password"
                value={this.state.password}
                onChange={this.handleChange}
                required
              />
            </div>
          </div>

          <div className="footer">
            <button className="button buttonshadow" type="submit">
               Login 
            </button>
          </div>
          
        </form>
      </div>
    );
  }
}
export default Login;