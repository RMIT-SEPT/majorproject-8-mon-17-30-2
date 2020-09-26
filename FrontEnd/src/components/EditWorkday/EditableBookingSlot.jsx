import React, { useState, useEffect } from "react";
import { Card, Button } from 'react-bootstrap';
import "../../css/WorkerSchedule.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function EditableBookingSlot(props) {

    const [services, setServices] = useState([]); 

    useEffect(() =>{
        setServices(props.bookingSlot.availableServices);
    },[]);  
    
    function editBookingSlot(){
    }
    
    function deleteBookingSlot(){
    }
    
    return (
        <>
        <Card className='workerbookingslot'
            bg="light"
            key={props.bookingSlot.id}>
            <Card.Body>
              <Card.Header>{props.bookingSlot.startTime} - {props.bookingSlot.endTime}</Card.Header>
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
                <Button onClick={editBookingSlot}>🖉</Button>
                <Button variant='danger' onClick={deleteBookingSlot}>🗑</Button>
            </Card.Footer>
          </Card>
          <br/>
        </>
    )
}

export default EditableBookingSlot;