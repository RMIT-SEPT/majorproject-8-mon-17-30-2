// import React, { Component } from "react";
// import "./Register.css";

// class Register extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       fullname: "",
//       address: "",
//       mobile: "",
//       username: "",
//       password: "",
//     };
//     this.handleSubmit = this.handleSubmit.bind(this);
//     this.handleChange = this.handleChange.bind(this);
//   }

//   handleSubmit(event) {
//     console.log(this.state);
//     alert("Submitted");
//   }
//   handleChange(event) {
//     this.setState({ [event.target.name]: event.target.value });
//   }

//   render() {
//     return (
//       <div className="Register">
//         <form onSubmit={this.handleSubmit}>
//           <header className="Register-header">Create an Account</header>

//           <div className="form">
//             <div className="form-input">
//               <label>Full Name:</label>
//               <input
//                 type="fullname"
//                 name="fullname"
//                 placeholder="John Doe"
//                 value={this.state.fullname}
//                 onChange={this.handleChange}
//                 required
//               />
//             </div>

//             <div className="form-input">
//               <label>Address:</label>
//               <input
//                 type="text"
//                 name="address"
//                 placeholder="123 Apple Street"
//                 value={this.state.address}
//                 onChange={this.handleChange}
//                 required
//               />
//             </div>

//             <div className="form-input">
//               <label>Phone Number:</label>
//               <input
//                 type="text"
//                 name="mobile"
//                 placeholder="12345678"
//                 value={this.state.mobile}
//                 onChange={this.handleChange}
//                 required
//               />
//             </div>

//             <div className="form-input">
//               <label>Username:</label>
//               <input
//                 type="text"
//                 name="username"
//                 placeholder="username"
//                 value={this.state.username}
//                 onChange={this.handleChange}
//                 required
//               />
//             </div>

//             <div className="form-input">
//               <label>Password:</label>
//               <input
//                 type="password"
//                 name="password"
//                 placeholder="password"
//                 value={this.state.password}
//                 onChange={this.handleChange}
//                 required
//               />
//             </div>
//           </div>

//           <div className="footer">
//             <button type="submit">Submit</button>
//           </div>
//         </form>
//       </div>
//     );
//   }
// }

// export default Register;
