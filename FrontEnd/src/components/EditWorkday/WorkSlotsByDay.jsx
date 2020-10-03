import React, { useState, useEffect } from "react";
import EditableBookingSlot from "./EditableBookingSlot";
import "../../css/WorkerSchedule.css";
import { Card, Button } from 'react-bootstrap';
import WorkerService from "../../services/WorkerService";

//props: workerId, date, workSlots, {addBookingSlot}, {deleteWorkSlot}
function WorkSlotsByDay(props){

  const [workSlots, setWorkSlots] = useState([]);

  useEffect(() => {

  },[]);

  return (props.workSlots && props.workSlots.length > 0 ? (
  // if there are >0 slots:
    <>
    <h1 class="blockquote text-center">{props.date}</h1>
    {props.workSlots.map((workSlot) => 
      <>
      <Card className='workday'
            bg="light"
            key={workSlot.id}
            border="dark">
          <Card.Body>
            <Card.Header className="bold">Shift: {workSlot.startTime} - {workSlot.endTime}</Card.Header>
            <Card.Title className="cardtitle"></Card.Title>
            <Card.Text className="cardtext">
            {workSlot.bookingSlots && workSlot.bookingSlots.length > 0 ? 
                workSlot.bookingSlots.map((bookingSlot) => 
                <EditableBookingSlot availableServices={props.availableServices} bookingSlot={bookingSlot} workSlot={workSlot} handleEditBookingSlot={props.handleEditBookingSlot}/>)
            : "No Booking Slots"}
            </Card.Text>
            <Button variant="secondary" className="new-booking-slot" onClick={() => props.addBookingSlot(workSlot.id)}>+</Button>
            <br/>
          </Card.Body>
          <br/>
          <Card.Footer className='text-right'>
            <Button onClick={() => props.handleEditWorkSlot(workSlot)}>✏️</Button><> </>
            {/* <Button variant='danger' onClick={props.deleteWorkSlot(props.workSlot)}>🗑️</Button> */}
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