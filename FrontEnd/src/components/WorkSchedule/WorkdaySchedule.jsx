import React, { useState, useEffect } from "react";
import WorkerBookingSlot from "./WorkerBookingSlot";
import "../../css/WorkerSchedule.css";
import { Card } from 'react-bootstrap';
import WorkerService from "../../services/WorkerService";
import moment from 'moment';

function WorkdaySchedule(workerId){

  // "dummydate" is a fixed date for testing, "date" uses the current day
  // change the parameter in useeffect/getWorkSlotByDate... to use real data
  var date = moment().format('yyyy-MM-DD').toString();
  var dummyDate = "2007-09-25";

  const [slots, setSlots] = useState([]);  

  useEffect(() =>{
    WorkerService.getWorkSlotByDateAndWorkerId(workerId.workerId, dummyDate).then((response) =>{
      setSlots(response.data.length ? response.data : []);
    });
  },[]);

  console.log(slots);

  return (slots.length ? (
    <>
    <h1 class="display-4 text-center">Assigned Working Hours<br/>Today - {date}</h1>
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
  <><h1 class="display-4 text-center">No Assigned Working Hours<br/>Today ({date})</h1></>)

}

export default WorkdaySchedule;