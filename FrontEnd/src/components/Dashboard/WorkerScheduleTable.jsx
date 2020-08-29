import React from "react";

function WorkerScheduleTable(){

    return(
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

    )

}

export default WorkerScheduleTable;