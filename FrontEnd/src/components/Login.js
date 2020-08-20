import React, { Component } from "react";
import "./Login.css";


class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    if(this.state.username === "test" && this.state.password === "test"){
      this.props.history.push(`/`);
      this.setState({showSuccessMessage:true});
      this.setState({hasLoginFailed:false});
    } else{
      this.setState({showSuccessMessage:false});
      this.setState({hasLoginFailed:true});
    }
    console.log(this.state);
    alert("Submitted");
  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  
  }

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit} method="POST">
          <header className="Login-header">AGME LOGIN</header>

          <div className="form">
          {this.state.hasLoginFailed && <div className="alert alert-warning"> Invalid Credentials </div>}
          {this.state.showSuccessMessage && <div> Login Succesful </div>}
            

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