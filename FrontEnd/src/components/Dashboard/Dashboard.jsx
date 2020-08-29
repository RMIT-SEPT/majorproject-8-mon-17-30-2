import React from "react";
import DashboardCard from "./DashboardCard";
import "../../css/Dashboard.css";
import Profile from "./Profile";
import AuthenticationService from "../Service/AuthenticationService";
import {WORKER} from "../../Utils/utils";


function Dashboard(props) {

  return (
    <div>
      <div className="card">
        <div className="card-header dashboard-heading-container">
          <h1 className="dashboard-heading">{props.title}</h1>
        </div>
        <div className="card-body">
        
          <Profile apiUrl={props.apiUrl}/>
          {!AuthenticationService.getRole() === WORKER ?  
            
          <div className="card-deck">
            {props.details.map(details => <DashboardCard key= {details.key} title={details.title} desc={details.desc} />)}
          </div>
          :
          <div> 
            <div className ="worker-assigned-hours-heading">
              <h1>Assigned Working Hours</h1>
            </div>
                <div className="container">
                <table className="table worker-table table-bordered ">
              
                    <thead className="thead-light">  
                        <tr>
                            <th>Date</th>
                            <th>Day</th>
                            <th>Time</th>
                            <th>Service</th>
                        </tr>
                    </thead>
                    <tbody>
                      <tr>
                          <td>
                            29/08/20
                          </td>
                          <td>
                            Saturday
                          </td>
                          <td>
                            10:00 - 13:00
                          </td>
                          <td>
                            Hairdressing
                        </td>
                      </tr>
                    </tbody>
                </table>
            </div>
          </div>

        }
         
      </div>
    </div>
      
  </div>

);
}

export default Dashboard;
