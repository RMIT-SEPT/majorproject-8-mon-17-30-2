import React, { useState, useEffect } from "react";
import WorkerBookingSlot from "./WorkerBookingSlot";
import "../../css/WorkerSchedule.css";
import { Card } from 'react-bootstrap';
import WorkerService from "../../services/WorkerService";
import {Link} from "react-router-dom";

//props: workerId, date (string in the format YYYY-MM-DD)
function WorkdaySchedule(props){


  const [slots, setSlots] = useState([]);  

  useEffect(() =>{
    WorkerService.getWorkSlotsByDateAndWorkerId(props.workerId, props.date).then((response) =>{
      setSlots(response.data.length ? response.data : []);
    });
  },[props.workerId, props.date]);

  return (

    <div className="text-center">
        {!props.noHead ? <h1 className="text-center">{props.date}</h1> :<></>}   
        {slots.length ? (
        // if there are >0 slots:
        <>
        {slots.map((workSlot) => 
          <>
          <Card className='workday'
              bg="light"
              key={workSlot.id}>
              <Card.Body>
                <Card.Header className="bold">Shift: {workSlot.startTime} - {workSlot.endTime}</Card.Header>
                <Card.Title className="cardtitle"></Card.Title>
                <div className="cardtext">
                
                {workSlot.bookingSlots.map((bookingSlot) => 
                  <WorkerBookingSlot key={bookingSlot.id} bookingSlot={bookingSlot}/>)}
                </div>
              </Card.Body>
            </Card>
            <br/>
            </>
          )}

        </>
        ) :
        // if there are no slots:
        <><h4>No Assigned Working Hours<br/></h4></>}
        {!props.noWeek ?
        <Link to={'/worker/' + props.workerId + '/work-slots/week'}>
          <button className="btn btn-info profile-btn">View Week</button>
        </Link>:<></>}

        <br/><br/>
    </div>

  )

}

export default WorkdaySchedule;