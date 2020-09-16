import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import AddEmployeeButton from "./AddEmployeeButton";
import WorkerService from "../../services/WorkerService";
import AuthenticationService from "../../services/AuthenticationService";

function EmployeeList(props){

    const [workers, setworkers] = useState([]);
    useEffect(()=>{
        console.log(AuthenticationService.getBusinessId());
        // WorkerService.getWorkersByBusiness
    },[])


    return(
        <div className="card list list-width">
            <ul className="list-group list-group-flush remove-list-bullet">
                <Link to="/" className="employee-link">
                    <button className="list-group-item custom-list-item task list-button">
                        <li> {`John`}</li>
                    </button>
                </Link>  
                <Link to="/">
                    <button className="list-group-item custom-list-item task list-button">
                        <li> {`John`}</li>
                    </button>
                </Link>     
                <Link to="/">
                    <button className="list-group-item custom-list-item task list-button">
                        <li> {`John`}</li>
                    </button>
                </Link>   
                
                <AddEmployeeButton />
            </ul>

      </div>
    );
}
export default EmployeeList;