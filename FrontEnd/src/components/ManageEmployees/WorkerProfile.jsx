import React, {useState, useEffect} from "react";
import "../../css/Dashboard.css";
import GetRequestService from "../../services/GetRequestService";
import WorkerScheduleTable from "../Dashboard/WorkerScheduleTable";
import {Link} from "react-router-dom";

function WorkerProfile(props){
      // TO access business ID use props.match.params.workerId
  
    const[userDetails, setUserDetails] = useState({});
    const [services, setServices] = useState([]); 
    useEffect(() => {
    
        GetRequestService.getRequestId(props.apiUrl, props.match.params.workerId)
        .then((response) => {        
            setUserDetails(response.data);
            setServices(response.data.services);    
            console.log(response.data);
        })
        .catch(() => {
            console.log("ERROR USER CANNOT BE FOUND");
        });

    }, [props.apiUrl, props.match.params.workerId]);

    return(
        
        <div className="card dashboard-card">
            <div className="card-header dashboard-heading-container">
            <h1 className="dashboard-heading">View Worker Details</h1>
            </div>
            <div className="jumbotron profile">
                <h1 className="display-4">{userDetails.name}</h1>
                <p className="lead">View Details</p>
                <hr className="my-4"/>
                <ul className="list-group profile-list">
                    <li className="list-group-item">Username: {userDetails.username}</li>
                    <li className="list-group-item">Name: {userDetails.name}</li>  
                    <li className="list-group-item">Address: {userDetails.address}</li>
                    <li className="list-group-item">Phone: {userDetails.phoneNumber}</li>
                    <li className="list-group-item">Role: WORKER</li>
                
                    <div className="service-heading">
                        <h2 className="display-4 text-center"> Services </h2> 
                        {services.map(service => <li className="list-group-item" key={service.id}> {service.title}</li>)}
                    </div>
                
                </ul>
                
                <Link to={`/workers/edit/${props.match.params.workerId}`}>
                    <button className="btn btn-info profile-btn">Edit Details here</button>
                </Link>
            
                
            
            </div>
        <WorkerScheduleTable/>
        </div>
       


    )
}
export default WorkerProfile;