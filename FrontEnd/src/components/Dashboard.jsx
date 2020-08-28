import React from "react";

function Dashboard(props) {
  return (
        <div className="container-fluid">
        <div style={{display: 'flex', justifyContent: 'center'}}></div>
          <h1 style={{display: 'flex', justifyContent: 'center'}}>{props.title}</h1>
          <p style={{display: 'flex', justifyContent: 'center'}}> {props.desc}</p>

          <div class="card-deck">
            <div class="card text-center" >
              <div class="card-body">
                <h4 class="card-title">View Profile</h4>
                <p class="card-text">
                  <small class="text-muted">Edit your details!</small>
                </p>
                <a href="#" class="btn btn-primary">
                  Click Here!
                </a>
              </div>
            </div>

            <div class="card text-center">
              <div class="card-body">
                <h4 class="card-title">Make a Booking</h4>
                <p class="card-text">
                  <small class="text-muted">Make Bookings!</small>
                </p>
                <a href="#" class="btn btn-primary">
                  Click Here!
                </a>
              </div>
            </div>

            <div class="card text-center">
              <div class="card-body">
                <h4 class="card-title">Booking History</h4>
                <p class="card-text">
                  <small class="text-muted">View successfully completed bookings</small>
                </p>
                <a href="#" class="btn btn-primary">
                  Click Here!
                </a>
              </div>
            </div>
          </div>
        </div>
   
  );
}

export default Dashboard;
