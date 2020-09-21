import React, { useState, useEffect } from "react";
import { Card, ToastBody } from 'react-bootstrap';
import "../../css/WorkerSchedule.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function WorkerBookingSlot(bookingSlot) {

    const [bookings, setBookings] = useState([]); 
    const [services, setServices] = useState([]); 

    useEffect(() =>{
        console.log("bookingslot:", bookingSlot);
        setBookings(bookingSlot.bookingSlot.bookings);
        setServices(bookingSlot.bookingSlot.availableServices);
    },[]);
    
    
    return (
        <>
        <Card className='workerbookingslot'
            bg="light"
            key={bookingSlot.id}>
            <Card.Body>
              <Card.Header>Booking Slot {bookingSlot.bookingSlot.startTime} - {bookingSlot.bookingSlot.endTime}</Card.Header>
              <Card.Title className="cardtitle"></Card.Title>
              <Card.Text className="cardtext">
              <table className="table worker-table table-bordered ">              
                <thead className="thead-light">  
                  <tr>
                      <td class="slottitle">Bookings{bookingSlot.bookingSlot.capacity}:</td>
                      <td>
                          {bookings.length ? bookings.map((booking) => <>Booking #{booking.id}: {booking.customerName}<br/></>) : "No bookings"}
                      </td>
                  </tr>
                  <tr>
                      <td class="slottitle">Available Services:</td>
                      <td>
                          {services.map((service) => <>{service.title}<br/></>)}
                      </td>
                  </tr>
                  <tr>
                      <td class="slottitle">Booked Service:</td>
                      <td>
                          {bookingSlot.bookingSlot.bookedService != null ? bookingSlot.bookingSlot.bookedService : "Not set"}
                      </td>
                  </tr>
                  <tr>
                      <td class="slottitle">Capacity:</td>
                      <td>
                          {bookingSlot.bookingSlot.bookedService != null ? bookingSlot.bookings.length + " / " + bookingSlot.bookingSlot.bookedService.capacity : "Not set"}
                      </td>
                  </tr>
                </thead>
              </table>

              </Card.Text>
            </Card.Body>
          </Card>
          <br/>
        </>
    )
}

export default WorkerBookingSlot;