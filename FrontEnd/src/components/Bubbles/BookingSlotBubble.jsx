import React, { useState } from 'react';
import { Card, Button } from 'react-bootstrap';
import "../../css/BookingHistory.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function BookingSlotBubble(props) {

  const [serviceId, setServiceId] = useState(null);
  
  function pass(){
    props.onChoose(props.bookingSlot.id, serviceId);
    console.log("booking slot is ", props.bookingSlot.id);
  }

  function handleChange(event) {
    const {name, value} = event.target;
    if(name === "service"){
      setServiceId(value);
    }
  }

  return (
    <>
      <Card className='bookingbubble'
        bg="light"
        key={props.bookingSlot.id}>
        <form onSubmit={pass}>
        <Card.Body>
          <Card.Header className="text-muted">Booking Slot #{props.bookingSlot.id}</Card.Header>
          <Card.Title className="cardtitle">{props.bookingSlot.businessName}</Card.Title>
          <Card.Text className="cardtext">
              <span className="bookingbubble-line">
                  <span className="bookingbubble-title">Date:</span>
                  <span className="bookingbubble-text">{props.bookingSlot.date}</span>                    
                  <br/>
              </span> 
              <span className="bookingbubble-line">
                  <span className="bookingbubble-title">Start Time:</span>
                  <span className="bookingbubble-text">{props.bookingSlot.startTime}</span>                    
                  <br/>
              </span> 
              <span className="bookingbubble-line">
                  <span className="bookingbubble-title">End Time:</span>
                  <span className="bookingbubble-text">{props.bookingSlot.endTime}</span>
                  <br/>
              </span> 
              <span className="bookingbubble-line">
                  <span className="bookingbubble-title">Worker:</span>
                  <span className="bookingbubble-text">{props.bookingSlot.workerName}</span>
                  <br/>
              </span>
              <span className="bookingbubble-line">
                  <span className="bookingbubble-title">Services:</span>
                  <span className="bookingbubble-text">
                    <select name="service" onChange={handleChange} required="true">
                      <option value="" disabled selected hidden>
                        Select an option
                      </option>
                      {props.bookingSlot.availableServices.map((service) => 
                      <option key={service.id} value={service.serviceTitle}></option>)}
                    </select> 
                  </span>
                  <br/>
              </span>            
          </Card.Text>
          <Card.Footer className="text-right">
            <Button size="sm" type="submit" >Book</Button>
          </Card.Footer>
        </Card.Body>
        </form>
      </Card>
      <br/>
    </>
  )

}

export default BookingSlotBubble;