import React from 'react';
import { Card, Button } from 'react-bootstrap';
import "../../css/BookingHistory.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function BookingSlotBubble(bookingSlot) {
    return (
        <>
        <Card className='bookingbubble'
            bg="light"
            key={bookingSlot.id}>
            <Card.Body>
              <Card.Header className="text-muted">Booking Slot #{bookingSlot.id}</Card.Header>
              <Card.Title className="cardtitle">{bookingSlot.businessName}</Card.Title>
              <Card.Text className="cardtext">
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Date:</span>
                    <span className="bookingbubble-text">{bookingSlot.date}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Start Time:</span>
                    <span className="bookingbubble-text">{bookingSlot.startTime}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">End Time:</span>
                    <span className="bookingbubble-text">{bookingSlot.endTime}</span>
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Worker:</span>
                    <span className="bookingbubble-text">{bookingSlot.workerName}</span>
                    <br/>
                </span>
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Services:</span>
                    <span className="bookingbubble-text">
                    <select name="service23">
                      <option value="" disabled selected hidden>
                        Select an option
                      </option>
                      {bookingSlot.availableServices.map((service) => 
                      <option key={service.id} value={service.serviceTitle}></option>)}
                    </select> 
                    </span>
                    <br/>
                </span>
              </Card.Text>
              <Card.Footer className="text-right">
                <Button size="sm" type="submit">Book</Button>
              </Card.Footer>
            </Card.Body>
          </Card>
          <br/>
        </>
    )
}

export default BookingSlotBubble;