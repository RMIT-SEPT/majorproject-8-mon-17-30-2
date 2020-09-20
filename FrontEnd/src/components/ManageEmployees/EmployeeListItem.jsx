import React from "react";
import {Link} from "react-router-dom";
function EmployeeListItem(props){
    return( 
        <Link to={`/worker/${props.id}`} className="employee-link">
            <button className="list-group-item custom-list-item task list-button">
                <li> {props.name}</li>
            </button>
        </Link>  
        );
}
export default EmployeeListItem;