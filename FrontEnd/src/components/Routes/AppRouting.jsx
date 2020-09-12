import React, {useState} from "react";
import Register from "../Register";
import Login from "../Login";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "../Nav/Navbar";
// import ListCustomerBookings from "../TESTINGListCustomerBookings";
import WorkerAuthenticatedRoute from "./WorkerAuthenticatedRoute";
import CustomerAuthenticatedRoute from "./CustomerAuthenticatedRoute";
import AdminAuthenticatedRoute from "./AdminAuthenticatedRoute";
import Dashboard from "../Dashboard/Dashboard";
import BookingHistory from "../BookingHistory/BookingHistory";
// import BookingPage from "../BookingPage";
import BookingSummary from "../AdminBookingSummary/BookingSummary"
import BookingPageTest from "../MakeABooking/BookingPageTest";
import AuthenticationService from "../../services/AuthenticationService";
import SelectBusinessPage from "../MakeABooking/SelectBusinessPage";
import PastBookingSummary from "../AdminBookingSummary/PastBookingSummary";
import Footer from "../Footer";
import {GET_CUSTOMER_URL, GET_ADMIN_URL, CUSTOMER_BUTTON_DETAILS, ADMIN_BUTTON_DETAILS, GET_WORKER_URL, CUSTOMER} from "../../Utils/utils";

// Routes the application
// Google React-Router-Dom to learn more about routing
function AppRouting() {
    const [isLoggedIn, setIsLoggedIn] = useState(AuthenticationService.isUserLoggedIn());
  return (
    <Router> 
    <div>
      <Route path="/" render={(props) => <Navbar {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}/>
      
      <Switch>
        {/* GENERAL ROUTES */}
        <Route path="/login" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>} />        
        <Route path="/register" exact component={Register} />
        {/* CUSTOMER ROUTES
         <CustomerAuthenticatedRoute path="/bookings/create" exact component={BookingPageTest}/> 
        */}
        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer Dashboard`} details={CUSTOMER_BUTTON_DETAILS} 
        apiUrl={AuthenticationService.getRole() === CUSTOMER ? GET_CUSTOMER_URL : GET_ADMIN_URL}/>} />       
        <CustomerAuthenticatedRoute path="/bookings/past" exact component={BookingHistory}/> 
        <CustomerAuthenticatedRoute path="/bookings/create/:businessId" component={BookingPageTest} />
        <CustomerAuthenticatedRoute path="/bookings/business" exact component={SelectBusinessPage}/> 
         {/* ADMIN ROUTES */}
        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <Dashboard {...props} title={`Admin Dashboard`} details={ADMIN_BUTTON_DETAILS} apiUrl={GET_ADMIN_URL}/>} />
        <AdminAuthenticatedRoute path="/bookings/summary" exact render={(props) => <BookingSummary {...props}/>} />
        <AdminAuthenticatedRoute path="/bookings/summary/past" exact render={(props) => <PastBookingSummary {...props} title={`Past Booking Summary`}/> } />
         {/* WORKER ROUTES */}
        <WorkerAuthenticatedRoute path="/worker" exact render={(props) => <Dashboard {...props} title={`Worker Dashboard`} apiUrl={GET_WORKER_URL} />} />
      </Switch>
      <Route path="/" render={(props) => <Footer />}/>
    
    </div>
    </Router>
  );
}

export default AppRouting;