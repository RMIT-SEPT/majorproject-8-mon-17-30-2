import React from "react";
import DashboardCard from "./DashboardCard";
import "../../css/Dashboard.css";
import Profile from "./Profile";
import AuthenticationService from "../../services/AuthenticationService";
import {WORKER} from "../../Utils/utils";
import WorkdaySchedule from "../WorkSchedule/WorkdaySchedule";
import moment from 'moment';

function Dashboard(props) {

  const today = moment().format("yyyy-MM-DD").toString()
  
  return (
    <div>
      <div className="card dashboard-card">
        <div className="card-header dashboard-heading-container">
          <h1 className="dashboard-heading">{props.title}</h1>
        </div>
        <div className="jumbotron-profile">
        
          <Profile apiUrl={props.apiUrl}/>
          {AuthenticationService.getRole() !== WORKER ?              
          // if not a worker display buttons
          <div className="card-deck dashboard-card-deck" >
            {props.details.map(details => <DashboardCard key= {details.key} title={details.title} desc={details.desc} link={details.link} />)}
          </div>
          :
          // otherwise display today's work schedule
          <><h1 className="display-4 container-fluid">Assigned Working Hours</h1>
          <WorkdaySchedule workerId={AuthenticationService.getLoggedInId()} date={today}/></>}
          
        </div>
      </div>      
    </div>

);
}

export default Dashboard;
