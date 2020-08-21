import React, { Component } from "react";
import "../css/Login.css";
import AuthenticationService from "./Service/AuthenticationService";


class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      hasLoginFailed: false,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this._isMounted = false;
  }
  componentDidMount() {
    this.setState({ hasLoginFailed: false });
    this._isMounted = true;
  }
  componentWillUnmount() {
    this._isMounted = false;
 }
  handleSubmit(event) {

    event.preventDefault();
    AuthenticationService
    .executeBasicAuthenticationService(this.state.username, this.state.password)
    .then((response) => {
        
        sessionStorage.setItem("Role", response.data.role);
        AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password);
        if(AuthenticationService.getRole() === "CUSTOMER"){
          this.props.history.push(`/customer`);
          this.props.setIsLoggedIn(true);
        } else if (AuthenticationService.getRole() === "ADMIN"){
          this.props.history.push(`/admin`);
          this.props.setIsLoggedIn(true);
        } else if (AuthenticationService.getRole() === "WORKER"){
          this.props.history.push(`/worker`);
          this.props.setIsLoggedIn(true);
        } else{
          AuthenticationService.logout();
          this.props.history.push(`/`);
        }


    

        
    }).catch(() => {
       
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
          {!this.props.authorised && <div className="alert alert-danger"> Unauthorised Request</div>}
         
            

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