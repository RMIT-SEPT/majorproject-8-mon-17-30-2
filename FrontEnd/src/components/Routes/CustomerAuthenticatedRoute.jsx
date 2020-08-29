import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../Service/AuthenticationService';
import {CUSTOMER, ADMIN} from "../../Utils/utils";

// Customer routes only
// use for customer only access
function CustomerAuthenticatedRoute(props) {
    if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === CUSTOMER || AuthenticationService.getRole() === ADMIN)) {
        return <Route {...props} />
    } else {
        return <Redirect to={{
            pathname: '/login',
            state: { authorised: false }
          }} />
    }
    
}

export default CustomerAuthenticatedRoute;