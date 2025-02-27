import React, { useState, useEffect } from "react";
import { Card, Button} from 'react-bootstrap';
import "../../css/WorkerSchedule.css";

//props: bookingSlot (services, startTime, endTime)
function EditableBookingSlot(props) {

    const [services, setServices] = useState([]); 

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
              <div className="cardtext">
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
              </div>
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