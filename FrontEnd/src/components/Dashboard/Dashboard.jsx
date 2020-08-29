import React from "react";
import DashboardCard from "./DashboardCard";
import "../../css/Dashboard.css";
import Profile from "./Profile";


function Dashboard(props) {

  return (
    <div>
      <div className="card">
        <div className="card-header dashboard-heading-container">
          <h1 className="dashboard-heading">{props.title}</h1>
        </div>
        <div className="card-body">
        
          <Profile apiUrl={props.apiUrl}/>
          <div className="card-deck">
          {props.details.map(details => <DashboardCard key= {details.key} title={details.title} desc={details.desc} />)}
        </div>
      </div>
    </div>
      
  </div>

);
}

export default Dashboard;
