import React from "react";
import DashboardCard from "./DashboardCard";
import "../../css/Dashboard.css";
import Profile from "./Profile";
import customerButtonDetails from "./DashboardButtonDetails";

function Dashboard(props) {
  const GET_CUSTOMER_URL = '/api/customer/';
  return (
        <div>

          <div className="card">
            <div className="card-header dashboard-heading-container">
              <h1 className="dashboard-heading">{props.title}</h1>
            </div>
            <div className="card-body">
            
              <Profile apiUrl={GET_CUSTOMER_URL}/>
              <div className="card-deck">
              {customerButtonDetails.map(details => <DashboardCard key= {details.key} title={details.title} desc={details.desc} />)}
            </div>
          </div>
        </div>
          
      </div>
   
  );
}

export default Dashboard;
