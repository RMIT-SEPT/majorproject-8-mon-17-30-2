import React from "react";
// import logo from "./logo.svg";
import "./App.css";
import Register from "./components/Register";
import Login from "./components/Login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "./components/Nav/Navbar";



function App() {
  return (
    <Router> 
    <div>
  
      <Route path="/" component={Navbar}/>
      <Route path="/login" component={Login} />
      <Route path="/register" component={Register} />

    
    </div>
    </Router>
  );
}

export default App;
