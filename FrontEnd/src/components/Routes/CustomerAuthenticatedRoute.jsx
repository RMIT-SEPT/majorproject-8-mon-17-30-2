import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationService from '../Service/AuthenticationService';

class CustomerAuthenticatedRoute extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn() && (AuthenticationService.getRole() === "CUSTOMER" || AuthenticationService.getRole() === "ADMIN")) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }

    }
}

export default CustomerAuthenticatedRoute