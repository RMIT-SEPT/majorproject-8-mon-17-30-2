import React from "react";
// import logo from "./logo.svg";
import "./App.css";
import Register from "./components/Register";
import Login from "./components/Login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "./components/Nav/Navbar";
import ListCustomerBookings from "./components/ListCustomerBookings";
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import WorkerAuthenticatedRoute from "./components/Routes/WorkerAuthenticatedRoute";
import CustomerAuthenticatedRoute from "./components/Routes/CustomerAuthenticatedRoute";
import AdminAuthenticatedRoute from "./components/Routes/AdminAuthenticatedRoute";
import Dashboard from "./components/Dashboard";



function App() {
  return (
    <Router> 
    <div>
      <Route path="/" component={Navbar}/>
      <Switch>
        
        <Route path="/login" exact component={Login} />
        <Route path="/register" exact component={Register} />
        <AuthenticatedRoute path="/bookings" exact component={ListCustomerBookings} />

        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer`} desc={`customer dashboard`} />} />
        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <Dashboard {...props} title={`Admin`} desc={`Admin dashboard`} />} />
        <WorkerAuthenticatedRoute path="/worker" exact render={(props) => <Dashboard {...props} title={`Worker`} desc={`worker dashboard`} />} />
        
      </Switch>
    
    </div>
    </Router>
  );
}

export default App;
