import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../Service/AuthenticationService';
// Admin Route only
// use for admin access
function AdminAuthenticatedRoute(props) {
    if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === "ADMIN")) {
        return <Route {...props} />
    } else {
        return <Redirect to="/login/unauthorised" />
    }
    
}

export default AdminAuthenticatedRoute;

