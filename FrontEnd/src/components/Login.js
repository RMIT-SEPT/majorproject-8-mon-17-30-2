import React, { Component } from "react";
import "./Login.css";
import AuthenticationService from "./Service/AuthenticationService";


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
    // if(this.state.username === "test" && this.state.password === "test"){
    //   this.props.history.push(`/bookings`)
    //   // this.setState({showSuccessMessage:true});
    //   // this.setState({hasLoginFailed:false});
    // } else{
    //   this.setState({showSuccessMessage:false});
    //   this.setState({hasLoginFailed:true});
    // }
    event.preventDefault();
    AuthenticationService
    .executeBasicAuthenticationService(this.state.username, this.state.password)
    .then((response) => {
        
        sessionStorage.setItem("Role", response.data.role);
        AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password);
        if(AuthenticationService.getRole() === "CUSTOMER"){
          this.props.history.push(`/customer`);
        } else if (AuthenticationService.getRole() === "ADMIN"){
          this.props.history.push(`/admin`);
        } else if (AuthenticationService.getRole() === "WORKER"){
          this.props.history.push(`/worker`);
        } else{
          AuthenticationService.logout();
          this.props.history.push(`/`);
        }


        console.log(AuthenticationService.getLoggedInUserName());
        console.log(AuthenticationService.getRole());
        this.setState({ showSuccessMessage: true });
        this.setState({ hasLoginFailed: false });
        
    }).catch(() => {
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
    })

  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  
  }

  render() {
    return (
      <div className="Login">
   
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
            <button className="button buttonshadow" type="submit" onClick = {this.handleSubmit}>
               Login 
            </button>
          </div>
          
  
      </div>
    );
  }
}
export default Login;