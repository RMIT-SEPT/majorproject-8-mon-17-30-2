import React, {useState, useEffect} from "react";
import "../../css/Dashboard.css";
import AuthenticationService from "../Service/AuthenticationService"
import GetRequestService from "../Service/GetRequestService";
import {CUSTOMER, WORKER, ADMIN} from "../../Utils/utils";

function Profile(props){
    const authenicatedUser = AuthenticationService.getLoggedInUserName();
   
    const[userDetails, setUserDetails] = useState({});

    useEffect(() => {
        GetRequestService.getRequestUsername(props.apiUrl, authenicatedUser)
        .then((response) => {
            setUserDetails(response.data);
        })
        .catch(() => {
            console.log("ERROR USER CANNOT BE FOUND");
        });

    }, [authenicatedUser, props.apiUrl]);

    return(
        <div className="jumbotron profile">
            <h1 className="display-4">Hi, {authenicatedUser}!</h1>
            <p className="lead">View your details</p>
            <hr className="my-4"/>
            <ul className="list-group profile-list">
                <li className="list-group-item">Username: {userDetails.username}</li>
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
                    <li className="list-group-item">Services: Service1, Service2, Service3</li>
               }
            </ul>
            { !AuthenticationService.getRole() === WORKER && 
                <button className="btn btn-info profile-btn">Edit Details here</button>
           }
            

        </div>


    )
}
export default Profile;