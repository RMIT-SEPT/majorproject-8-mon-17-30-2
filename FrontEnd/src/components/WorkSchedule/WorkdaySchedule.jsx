import React, { useState, useEffect } from "react";
import WorkerBookingSlot from "./WorkerBookingSlot";
import "../../css/WorkerSchedule.css";
import { Card } from 'react-bootstrap';
import WorkerService from "../../services/WorkerService";

//props: workerId, date (string in the format YYYY-MM-DD)
function WorkdaySchedule(props){

  // var date = "2007-09-25";

  const [slots, setSlots] = useState([]);  

  useEffect(() =>{
    WorkerService.getWorkSlotByDateAndWorkerId(props.workerId, props.date).then((response) =>{
      setSlots(response.data.length ? response.data : []);
    });
  },[]);

  return (slots.length ? (
  // if there are >0 slots:
    <>
    <h1 class="blockquote text-center">Assigned Working Hours<br/>Today - {props.date}</h1>
    {slots.map((workSlot) => 
      <>
      <Card className='workday'
          bg="light"
          key={workSlot.id}>
          <Card.Body>
            <Card.Header className="bold">Shift: {workSlot.startTime} - {workSlot.endTime}</Card.Header>
            <Card.Title className="cardtitle"></Card.Title>
            <Card.Text className="cardtext">
            {workSlot.bookingSlots.map((bookingSlot) => 
              <WorkerBookingSlot bookingSlot={bookingSlot}/>)}
            </Card.Text>
          </Card.Body>
        </Card>
        <br/>
        </>
      )}

    </>
  ) :
  // if there are 0 slots:
  <><h1 class="blockquote text-center">No Assigned Working Hours<br/>Today ({props.date})</h1></>)

}

export default WorkdaySchedule;