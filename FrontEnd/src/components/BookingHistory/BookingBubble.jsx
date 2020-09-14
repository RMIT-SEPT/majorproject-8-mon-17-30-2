import React from 'react';
import Card from 'react-bootstrap/Card';
import "../../css/BookingHistory.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function BookingBubble(props) {
    return (
        <div>
        <Card className='bookingbubble' bg="light">
            <Card.Body>
              <Card.Header className="text-muted">Booking #{props.booking.id}</Card.Header>
              <Card.Title className="cardtitle">John's Barbershop{props.booking.business}</Card.Title>
              <Card.Text className="cardtext">
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Service:</span>
                    <span className="bookingbubble-text">{props.booking.serviceTitle}</span>
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Date:</span>
                    <span className="bookingbubble-text">{props.booking.date}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Start Time:</span>
                    <span className="bookingbubble-text">{props.booking.startTime}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">End Time:</span>
                    <span className="bookingbubble-text">{props.booking.endTime}</span>
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Worker:</span>
                    <span className="bookingbubble-text">{props.booking.workerName}</span>
                    <br/>
                </span>
              </Card.Text>
              <Card.Footer className="text-right text-muted">
                Completed
              </Card.Footer>
            </Card.Body>
          </Card>
          <br/>
        </div>
    )
}

export default BookingBubble;