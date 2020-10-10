import React from "react";
import {Link} from "react-router-dom";

function SignupOptions(){

    return(
        <>
            <div className="text-center">

                <br/><br/><header className="display-4">Create an Account</header><br/>

                <Link className="card text-center dashboard-link" to="/register/customer">
                    <button className="btn btn-light">
                    <div className="card-body card-body-dashboard">
                    <h4 className="card-title">Register as a Customer</h4>
                    <p className="card-text">
                        <small className="text-muted">And make bookings at all your favourite businesses</small>
                    </p>                             
                    </div>
                    </button>
                </Link>
                <Link className="card text-center dashboard-link" to="/register/business">
                    <button className="btn btn-light">
                    <div className="card-body card-body-dashboard">
                    <h4 className="card-title">Register your Business</h4>
                    <p className="card-text">
                        <small className="text-muted">And signup as its Admin</small>
                    </p>                             
                    </div>
                    </button>
                </Link> 
                                
            </div>
        </>
    )

}

export default SignupOptions;