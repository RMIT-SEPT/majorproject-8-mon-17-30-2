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
import AuthenticationService from "../Service/AuthenticationService"
import Footer from "../Footer";
import {GET_CUSTOMER_URL, GET_ADMIN_URL, CUSTOMER_BUTTON_DETAILS, ADMIN_BUTTON_DETAILS} from "../../Utils/utils"




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
        <Route path="/login" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} justRegistered={false} authorised ={true}/>} />
        
        <Route path="/register" exact component={Register} />
        {/*
          CUSTOMER ROUTES
        */}
        <CustomerAuthenticatedRoute path="/bookings" exact component={ListCustomerBookings} />
        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer Dashboard`} details={CUSTOMER_BUTTON_DETAILS} apiUrl={GET_CUSTOMER_URL}/>} />
         {/*
          ADMIN ROUTES
        */}
        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <Dashboard {...props} title={`Customer Dashboard`} details={ADMIN_BUTTON_DETAILS} apiUrl={GET_ADMIN_URL}/>} />
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
