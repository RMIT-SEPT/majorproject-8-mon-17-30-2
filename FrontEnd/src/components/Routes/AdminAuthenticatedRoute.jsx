import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../../services/AuthenticationService';
import {ADMIN} from "../../Utils/utils";

// Admin Route only
// use for admin access
function AdminAuthenticatedRoute(props) {
    if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === ADMIN)) {
        return <Route {...props} />
    } else {
        return <Redirect to={{
            pathname: '/login',
            state: { authorised: false }
          }} />
    }
    
}

export default AdminAuthenticatedRoute;

