import React, {useState, useEffect} from "react";
import "../../css/Dashboard.css";
import AuthenticationService from "../Service/AuthenticationService"
import GetRequestService from "../Service/GetRequestService";

function Profile(props){
    const authenicatedUser = AuthenticationService.getLoggedInUserName();
   
    const[userDetails, setUserDetails] = useState({});

    useEffect(() => {
        GetRequestService.getRequestUsername(props.apiUrl, authenicatedUser)
        .then((response) => {
            console.log(response.data);
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
                {AuthenticationService.getRole() === "CUSTOMER" ?    
                <div>
                    <li className="list-group-item">Address: {userDetails.address}</li>
                    <li className="list-group-item">Phone: {userDetails.phoneNumber}</li>
                </div>
                :
                    <li className="list-group-item">Business: {userDetails.business}</li>
                }
             
                <li className="list-group-item">Role: {AuthenticationService.getRole()}</li>
            </ul>
            <button className="btn btn-info profile-btn">Edit Details here</button>

        </div>


    )
}
export default Profile;