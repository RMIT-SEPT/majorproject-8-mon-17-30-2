import React, {useEffect, useState} from "react";




function EmployeeList(props){


    return(
        <div className="card list list-width">
            <ul className="list-group list-group-flush remove-list-bullet">
                <button className="list-group-item custom-list-item task list-button">
                    <li> {`John`}</li>
                </button>
            </ul>
      </div>
    );
}
export default EmployeeList;