import React from "react";
import {Link} from "react-router-dom";
import "../../css/BookingHistory.css";
function DashboardCard(props){

    return(
      <Link className="card text-center dashboard-link" to={props.link}>
        <button className="btn btn-light">
        <div className="card-body card-body-dashboard">
          <h4 className="card-title">{props.title}</h4>
          <p className="card-text">
            <small className="text-muted">{props.desc}</small>
          </p>
         
           <p className="card-text">Click Here!</p> 
       
        </div>
        </button>
      </Link>      
    
    )



}

export default DashboardCard;