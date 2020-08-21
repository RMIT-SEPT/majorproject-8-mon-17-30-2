import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../Service/AuthenticationService';

class AdminAuthenticatedRoute extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn() && AuthenticationService.getRole() === "ADMIN") {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login/unauthorised" />
        }

    }
}

export default AdminAuthenticatedRoute;