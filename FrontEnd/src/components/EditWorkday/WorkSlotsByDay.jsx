import React, { useState, useEffect } from "react";
import EditableBookingSlot from "./EditableBookingSlot";
import "../../css/WorkerSchedule.css";
import { Card, Button } from 'react-bootstrap';
import WorkerService from "../../services/WorkerService";

//props: workerId, date, workSlots, {addBookingSlot}, {deleteWorkSlot}
function WorkSlotsByDay(props){

  return (props.workSlots && props.workSlots.length > 0 ? (
  // if there are >0 slots:
    <>
    <h1 class="blockquote text-center">{props.date}</h1>
    {props.workSlots.map((workSlot) => 
      <>
      <Card className='workday'
          bg="light"
          key={workSlot.id}>
          <Card.Body>
            <Card.Header className="bold">{workSlot.startTime} - {workSlot.endTime}</Card.Header>
            <Card.Title className="cardtitle"></Card.Title>
            <Card.Text className="cardtext">
            {workSlot.bookingSlots && workSlot.bookingSlots.length > 0 ? 
                workSlot.bookingSlots.map((bookingSlot) => 
                <EditableBookingSlot availableServices={props.availableServices} bookingSlot={bookingSlot}/>)
            : "No Booking Slots"}
            </Card.Text>
            <Button variant="secondary" className="new-booking-slot" onClick={() => props.addBookingSlot(workSlot.id)}>+</Button>
          </Card.Body>
          <br/>
          <Card.Footer className='text-right'>
            <Button variant='danger' onClick={props.deleteWorkSlot}>Delete</Button>
          </Card.Footer>
        </Card>
        <br/>
        </>
      )}

    </>
  ) :
  // if there are 0 slots:
  <><h1 class="blockquote text-center">No Assigned Working Hours<br/>({props.date})</h1></>)

}

export default WorkSlotsByDay;