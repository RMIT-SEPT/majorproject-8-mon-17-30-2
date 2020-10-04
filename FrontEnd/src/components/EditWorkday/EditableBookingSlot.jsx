import React, { useState, useEffect } from "react";
import { Card, Button, Modal } from 'react-bootstrap';
import AddBookingSlot from "./AddBookingSlot"
import "../../css/WorkerSchedule.css";

//props: bookingSlot (services, startTime, endTime)
function EditableBookingSlot(props) {

    const [services, setServices] = useState([]); 
    const [workslot, setWorkslot] = useState();
    const [availableServices, setAvailableServices] = useState([]);

    useEffect(() =>{
        setServices(props.bookingSlot.availableServices);
    },[props.bookingSlot.availableServices]);  
       
    return (
        <>
        <Card className='workerbookingslot'
            bg="light"
            key={props.bookingSlot.id}>
            <Card.Body>
              <Card.Header>Booking Slot: {props.bookingSlot.startTime} - {props.bookingSlot.endTime}</Card.Header>
              <Card.Title className="cardtitle"></Card.Title>
              <Card.Text className="cardtext">
              <table className="table worker-table table-bordered ">              
                <thead className="thead-light">  
                  <tr>
                      <td>Available Services:</td>
                      <td>
                          {services.map((service) => <>{service.title}<br/></>)}
                      </td>
                  </tr>
                </thead>
              </table>
              </Card.Text>
            </Card.Body>
            <Card.Footer className="text-right text-muted">
                <Button size="sm" onClick={() => props.handleEditBookingSlot(props.bookingSlot)}>✏️</Button><> </>
                <Button size="sm" variant='danger' onClick={() => props.deleteBookingSlot(props.bookingSlot.id)}>🗑️</Button>
            </Card.Footer>
          </Card>
          <br/>
        </>        
    )

}

export default EditableBookingSlot;