import React, {useState} from "react";
import Register from "../Register";
import Login from "../Login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "../Nav/Navbar";
import ListCustomerBookings from "../ListCustomerBookings";
import WorkerAuthenticatedRoute from "./WorkerAuthenticatedRoute";
import CustomerAuthenticatedRoute from "./CustomerAuthenticatedRoute";
import AdminAuthenticatedRoute from "./AdminAuthenticatedRoute";
import Dashboard from "../Dashboard/Dashboard";
import AdminDashboard from "../Dashboard/AdminDashboard";
import AuthenticationService from "../Service/AuthenticationService"
import Footer from "../Footer";



// Routes the application
// Google React-Router-Dom to learn more about routing
function AppRouting() {
    const [isLoggedIn, setIsLoggedIn] = useState(AuthenticationService.isUserLoggedIn());
  return (
    <Router> 
    <div>
      <Route path="/" render={(props) => <Navbar {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}/>
      
      <Switch>
         {/*
          GENERAL ROUTES
        */}
        <Route path="/login" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} authorised={true}/>} />
        <Route path="/login/unauthorised" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} authorised={false}/>} />
        <Route path="/register" exact component={Register} />
        {/*
          CUSTOMER ROUTES
        */}
        <CustomerAuthenticatedRoute path="/bookings" exact component={ListCustomerBookings} />
        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer Dashboard`} />} />
         {/*
          ADMIN ROUTES
        */}
        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <AdminDashboard {...props} title={`Admin Dashboard`}/>} />
         {/*
          WORKER ROUTES
        */}
        <WorkerAuthenticatedRoute path="/worker" exact render={(props) => <Dashboard {...props} title={`Worker`} desc={`worker dashboard`} />} />
        
        
      </Switch>
      <Route path="/" render={(props) => <Footer />}/>
    
    </div>
    </Router>
  );
}

export default AppRouting;
