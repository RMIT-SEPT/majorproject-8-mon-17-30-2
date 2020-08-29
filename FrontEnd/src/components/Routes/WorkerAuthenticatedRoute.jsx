import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../../services/AuthenticationService';
import {WORKER, ADMIN} from "../../utils/utils";
// Worker routes only
// use for Worker only access
function WorkerAuthenticatedRoute(props) {
    if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === WORKER || AuthenticationService.getRole() === ADMIN)) {
        return <Route {...props} />
    } else {
        return <Redirect to={{
            pathname: '/login',
            state: { authorised: false }
          }} />
    }
    
}

export default WorkerAuthenticatedRoute;