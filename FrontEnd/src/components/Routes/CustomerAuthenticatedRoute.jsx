import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../Service/AuthenticationService';
// Customer routes only
// use for customer only access
function CustomerAuthenticatedRoute(props) {
    if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === "CUSTOMER" || AuthenticationService.getRole() === "ADMIN")) {
        return <Route {...props} />
    } else {
        return <Redirect to="/login/unauthorised" />
    }
    
}

export default CustomerAuthenticatedRoute;