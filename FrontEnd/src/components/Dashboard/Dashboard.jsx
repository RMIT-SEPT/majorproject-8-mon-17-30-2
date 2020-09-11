import React from "react";
import DashboardCard from "./DashboardCard";
import "../../css/Dashboard.css";
import Profile from "./Profile";
import AuthenticationService from "../../services/AuthenticationService";
import {WORKER} from "../../Utils/utils";
import WorkerScheduleTable from "./WorkerScheduleTable";


function Dashboard(props) {

  return (
    <div>
      <div className="card">
        <div className="card-header dashboard-heading-container">
          <h1 className="dashboard-heading">{props.title}</h1>
        </div>
        <div className="card-body">
        
          <Profile apiUrl={props.apiUrl}/>
          {AuthenticationService.getRole() !== WORKER ?  
            
          <div className="card-deck dashboard-card-deck" >
            {props.details.map(details => <DashboardCard key= {details.key} title={details.title} desc={details.desc} link={details.link} />)}
          </div>
          :
            <WorkerScheduleTable/>
        }
         
      </div>
    </div>
      
  </div>

);
}

export default Dashboard;
