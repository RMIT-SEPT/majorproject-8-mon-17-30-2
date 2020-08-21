import React, {useState} from "react";
import Register from "./Register";
import Login from "./Login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "./Nav/Navbar";
import ListCustomerBookings from "./ListCustomerBookings";
import WorkerAuthenticatedRoute from "./Routes/WorkerAuthenticatedRoute";
import CustomerAuthenticatedRoute from "./Routes/CustomerAuthenticatedRoute";
import AdminAuthenticatedRoute from "./Routes/AdminAuthenticatedRoute";
import Dashboard from "./Dashboard";
import AuthenticationService from "./Service/AuthenticationService"




function AppRouting() {
    const [isLoggedIn, setIsLoggedIn] = useState(AuthenticationService.isUserLoggedIn());
  return (
    <Router> 
    <div>
      <Route path="/" render={(props) => <Navbar {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}/>
      <Switch>
        
        <Route path="/login" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} authorised={true}/>} />
        <Route path="/login/unauthorised" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} authorised={false}/>} />
        <Route path="/register" exact component={Register} />
        <CustomerAuthenticatedRoute path="/bookings" exact component={ListCustomerBookings} />

        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer`} desc={`customer dashboard`} />} />
        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <Dashboard {...props} title={`Admin`} desc={`Admin dashboard`} />} />
        <WorkerAuthenticatedRoute path="/worker" exact render={(props) => <Dashboard {...props} title={`Worker`} desc={`worker dashboard`} />} />
        
      </Switch>
    
    </div>
    </Router>
  );
}

export default AppRouting;
