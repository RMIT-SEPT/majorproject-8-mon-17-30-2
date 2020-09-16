import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import AddEmployeeButton from "./AddEmployeeButton";



function EmployeeList(props){


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