import React, { useState, useEffect } from "react";
import { Card, ToastBody } from 'react-bootstrap';
import "../../css/WorkerSchedule.css";

//this component is going to be a single grey bubble displaying the data of a single booking
function WorkerBookingSlot(props) {

    const [bookings, setBookings] = useState([]); 
    const [services, setServices] = useState([]); 

    useEffect(() =>{
        setBookings(props.bookingSlot.bookings);
        setServices(props.bookingSlot.availableServices);
    },[]);
    
    
    return (
        <>
        <Card className='workerbookingslot'
            bg="light"
            key={props.id}>
            <Card.Body>
              <Card.Header>Booking Slot: {props.bookingSlot.startTime} - {props.bookingSlot.endTime}</Card.Header>
              <Card.Title className="cardtitle"></Card.Title>
              <Card.Text className="cardtext">
              <table className="table worker-table table-bordered ">              
                <thead className="thead-light">  
                  <tr>
                      <td class="slottitle">Bookings:</td>
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
                          {props.bookingSlot.bookedService != null ? props.bookingSlot.bookedService : "Not set"}
                      </td>
                  </tr>
                  <tr>
                      <td class="slottitle">Capacity:</td>
                      <td>
                          {props.bookingSlot.bookedService != null ? props.bookings.length + " / " + props.bookingSlot.bookedService.capacity : "Not set"}
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