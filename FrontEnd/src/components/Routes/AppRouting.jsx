import React, {useState} from "react";
import RegisterCustomer from "../RegisterCustomer";
import SignupOptions from "../SignupOptions";
import AdminBusinessSignup from "../Business/AdminBusinessSignup";
import Login from "../Login";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "../Nav/Navbar";
import WorkerAuthenticatedRoute from "./WorkerAuthenticatedRoute";
import CustomerAuthenticatedRoute from "./CustomerAuthenticatedRoute";
import AdminAuthenticatedRoute from "./AdminAuthenticatedRoute";
import Dashboard from "../Dashboard/Dashboard";
import BookingHistory from "../BookingHistory/BookingHistory";
import BookingSummary from "../AdminBookingSummary/BookingSummary";
import BookingPage from "../MakeABooking/BookingPage";
import AuthenticationService from "../../services/AuthenticationService";
import SelectBusinessPage from "../MakeABooking/SelectBusinessPage";
import PastBookingSummary from "../AdminBookingSummary/PastBookingSummary";
import NewBookingSummary from "../AdminBookingSummary/NewBookingSummary";
import CurrentBookings from "../CurrentBookings/CurrentBookings";
import ManageEmployeePage from "../ManageEmployees/ManageEmployeePage";
import WorkerProfile from "../ManageEmployees/WorkerProfile";
import WeekSchedule from "../WorkSchedule/WeekSchedule";
import EditEmployeePage from "../ManageEmployees/EditEmployeePage";
import AddEmployeePage from "../ManageEmployees/AddEmployeePage";
import EditCustomer from "../Customer/EditCustomer";
import AboutAndContact from "../HomePage/AboutAndContact";
import ViewAvailability from "../Business/ViewAvailability";
import Footer from "../Footer";
import {GET_CUSTOMER_URL, GET_ADMIN_URL, CUSTOMER_BUTTON_DETAILS, ADMIN_BUTTON_DETAILS, GET_WORKER_URL, CUSTOMER} from "../../Utils/utils";
import EditEmployeeWorkday from "../EditWorkday/EditEmployeeWorkday";

// Routes the application
function AppRouting() {
    const [isLoggedIn, setIsLoggedIn] = useState(AuthenticationService.isUserLoggedIn());
  return (
    <Router> 
    <div>
      <Route path="/" render={(props) => <Navbar {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}/>
      
      <Switch>
        {/* GENERAL ROUTES */}
        <Route path="/" exact render={(props) => <Login {...props} isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>} />  
        <Route path="/register" exact component={SignupOptions} />      
        <Route path="/register/customer" exact component={RegisterCustomer} />
        <Route path="/register/business" exact component={AdminBusinessSignup} />
        <Route path="/about" exact component={AboutAndContact} />

        {/* CUSTOMER ROUTES*/}

        <CustomerAuthenticatedRoute path="/customer" exact render={(props) => <Dashboard {...props} title={`Customer Dashboard`} details={CUSTOMER_BUTTON_DETAILS} 
        apiUrl={AuthenticationService.getRole() === CUSTOMER ? GET_CUSTOMER_URL : GET_ADMIN_URL}/>} />   
        <CustomerAuthenticatedRoute path="/customer/edit" exact render={(props) => <EditCustomer {...props} customerId={AuthenticationService.getLoggedInId()}/>} />
        <CustomerAuthenticatedRoute path="/bookings/past" exact component={BookingHistory}/> 
        <CustomerAuthenticatedRoute path="/bookings/create/:businessId" exact component={BookingPage} />
        <CustomerAuthenticatedRoute path="/bookings/business" exact component={SelectBusinessPage}/> 
        <CustomerAuthenticatedRoute path="/bookings/manage" exact component={CurrentBookings}/> 

         {/* ADMIN ROUTES */}

        <AdminAuthenticatedRoute path="/admin" exact render={(props) => <Dashboard {...props} title={`Admin Dashboard`} details={ADMIN_BUTTON_DETAILS} apiUrl={GET_ADMIN_URL}/>} />
        <AdminAuthenticatedRoute path="/bookings/summary" exact render={(props) => <BookingSummary {...props}/>} />
        <AdminAuthenticatedRoute path="/bookings/summary/past" exact render={(props) => <PastBookingSummary {...props} title={`Past Booking Summary`}/> } /> 
        <AdminAuthenticatedRoute path="/bookings/summary/new" exact render={(props) => <NewBookingSummary {...props} title={`Upcoming Bookings`}/> } /> 
        <AdminAuthenticatedRoute path="/workers" exact render={(props) => <ManageEmployeePage {...props}/> } />
        <AdminAuthenticatedRoute path="/worker/:workerId" exact render={(props) => <WorkerProfile {...props} apiUrl={GET_WORKER_URL}/> } />
        <AdminAuthenticatedRoute path="/worker/:workerId/edit" exact render={(props) => <EditEmployeePage {...props}/> } />
        <AdminAuthenticatedRoute path="/workers/add" exact render={(props) => <AddEmployeePage {...props}/> } />
        <AdminAuthenticatedRoute path="/worker/:workerId/work-slots/edit" exact render={(props) => <EditEmployeeWorkday {...props}/> } />
        <AdminAuthenticatedRoute path="/workers/availability" exact component={ViewAvailability}/> 

         {/* WORKER ROUTES */}

        <WorkerAuthenticatedRoute path="/worker" exact render={(props) => <Dashboard {...props} title={`Worker Dashboard`} apiUrl={GET_WORKER_URL} />} />
        <WorkerAuthenticatedRoute path="/worker/:workerId/work-slots/week" exact render={(props) => <WeekSchedule {...props} apiUrl={GET_WORKER_URL} />} />

      </Switch>
      <Route path="/" render={(props) => <Footer />}/>
    
    </div>
    </Router>
  );
}

export default AppRouting;