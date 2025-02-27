import React, { Component } from "react";
import "../css/Register.css";
import PostRequestService from "../services/PostRequestService";
import {POST_CUSTOMER_URL} from "../Utils/utils";

class RegisterCustomer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fullname: "",
      username: "",
      password: "",
      address: "",
      mobile: "",
      email: "",
      hasRegisterFailed: false,
     
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    console.log(this.state);
    const customer = {
      name: this.state.fullname,
      username: this.state.username,
      password: this.state.password,
      address: this.state.address,
      email:this.state.email,
      phoneNumber: this.state.mobile
    };
   
    PostRequestService.postRequest(POST_CUSTOMER_URL, customer)
          .then(response => {
            if(response.data != null){
              this.setState(this.intialState);
              this.props.history.push({
                pathname: '/',
                state: {justRegistered: true, authorised: true}
              });
            }
          }).catch(() => {
            this.setState({hasRegisterFailed: true });
          });
         
          
  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  render() {
    return (
      <div className="Register" method="POST">  
        <form onSubmit={this.handleSubmit}>
          <header className="display-4">Create a Customer Account</header>

          <div className="form">
          {this.state.hasRegisterFailed && <div className="alert alert-danger"> Invalid Credentials </div>}
            <div className="form-input">
              <label>Full Name:</label>
              <input
                type="fullname"
                name="fullname"
                placeholder="John Doe"
                value={this.state.fullname}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-input">
              <label>Address:</label>
              <input
                type="text"
                name="address"
                placeholder="123 Apple Street"
                value={this.state.address}
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-input">
              <label>Phone Number:</label>
              <input
                type="text"
                name="mobile"
                placeholder="12345678"
                value={this.state.mobile}
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-input">
            <label>email:</label>
            <input
              type="text"
              name="email"
              placeholder="john.doe@email.com"
              value={this.state.email}
              onChange={this.handleChange}
              required
            />
          </div>

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
              Register
            </button>
          </div>
        </form>
      </div>
    );
  }
}
export default RegisterCustomer;
