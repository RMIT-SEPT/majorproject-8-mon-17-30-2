import React, {useState, useEffect} from "react";
import "../../css/Dashboard.css";
import {Link} from "react-router-dom";
import AuthenticationService from "../../services/AuthenticationService"
import GetRequestService from "../../services/GetRequestService";
import {CUSTOMER, WORKER, ADMIN, BUSINESS_ID_SESSION_ATTRIBUTE} from "../../Utils/utils";

function Profile(props){
    const authenticatedUser = AuthenticationService.getLoggedInUserName();
    const authenticatedUserId = AuthenticationService.getLoggedInId();
   
    const[userDetails, setUserDetails] = useState({});
    const [services, setServices] = useState([]); 
    useEffect(() => {
        // GetRequestService.getRequestUsername(props.apiUrl, authenticatedUser)
        GetRequestService.getRequestId(props.apiUrl, authenticatedUserId)
        .then((response) => {        
            setUserDetails(response.data);
            setServices(response.data.services);   
            if(response.data.businessId){
                sessionStorage.setItem(BUSINESS_ID_SESSION_ATTRIBUTE, response.data.businessId);
            }  
        })
        .catch(() => {
            console.log("ERROR USER CANNOT BE FOUND");
        });

    }, [authenticatedUserId, props.apiUrl]);

    return(
        <div className="jumbotron profile">
            <h1 className="display-4">Hi, {authenticatedUser}!</h1>
            <p className="lead">View your details</p>
            <hr className="my-4"/>
            <ul className="list-group profile-list">
                <li className="list-group-item">Username: {userDetails.username}</li>
                {AuthenticationService.getRole() === CUSTOMER &&
                <li className="list-group-item">Email: {userDetails.email}</li>}
                <li className="list-group-item">Name: {userDetails.name}</li>
                {(AuthenticationService.getRole() === CUSTOMER || AuthenticationService.getRole() === WORKER) &&    
                <div>
                    <li className="list-group-item">Address: {userDetails.address}</li>
                    <li className="list-group-item">Phone: {userDetails.phoneNumber}</li>
                </div>
                }
                { AuthenticationService.getRole() === ADMIN && 
                     <li className="list-group-item">Business: {userDetails.businessname}</li>
                }             
                <li className="list-group-item">Role: {AuthenticationService.getRole()}</li>
                { AuthenticationService.getRole() === WORKER && 
                    <div className="service-heading">
                        <h2 className="display-4 text-center"> Services </h2> 
                        {services.map(service => <li className="list-group-item" key={service.id}> {service.title}</li>)}
                   </div>
                }
            </ul>
            {AuthenticationService.getRole() === ADMIN && 
                <button className="btn btn-info profile-btn">Edit Details here</button>
            }
            {AuthenticationService.getRole() === CUSTOMER && 
            <Link to={'/customer/edit'}>
                <button className="btn btn-info profile-btn">Edit Details here</button>
            </Link>}
            

        </div>


    )
}
export default Profile;