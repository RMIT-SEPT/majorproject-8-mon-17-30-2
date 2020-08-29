import React from "react";

function DashboardCard(props){

    return(
        <div className="card text-center">
        <button className="btn btn-light">
        <div className="card-body">
          <h4 className="card-title">{props.title}</h4>
          <p className="card-text">
            <small className="text-muted">{props.desc}</small>
          </p>
         
           <p className="card-text">Click Here!</p> 
       
        </div>
        </button>
      </div>
    )



}

export default DashboardCard;