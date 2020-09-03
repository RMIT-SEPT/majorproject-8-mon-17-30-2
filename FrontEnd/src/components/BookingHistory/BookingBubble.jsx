import React from 'react';
import Card from 'react-bootstrap/Card';
import "../../css/BookingHistory.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function BookingBubble(booking) {
    return (
        <>
        <Card className='bookingbubble'
            bg="light"
            key={booking.id}>
            <Card.Body>
              <Card.Header className="text-muted">Booking #{booking.id}</Card.Header>
              <Card.Title className="cardtitle">John's Barbershop{booking.business}</Card.Title>
              <Card.Text className="cardtext">
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Service:</span>
                    <span className="bookingbubble-text">{booking.serviceTitle}</span>
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Date:</span>
                    <span className="bookingbubble-text">{booking.date}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Start Time:</span>
                    <span className="bookingbubble-text">{booking.startTime}</span>                    
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">End Time:</span>
                    <span className="bookingbubble-text">{booking.endTime}</span>
                    <br/>
                </span> 
                <span className="bookingbubble-line">
                    <span className="bookingbubble-title">Worker:</span>
                    <span className="bookingbubble-text">{booking.workerName}</span>
                    <br/>
                </span>
              </Card.Text>
              <Card.Footer className="text-right text-muted">
                Completed
              </Card.Footer>
            </Card.Body>
          </Card>
          <br/>
        </>
    )
}

export default BookingBubble;