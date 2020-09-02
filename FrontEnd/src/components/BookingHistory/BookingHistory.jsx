import React, { Component } from "react";
import "../../css/BookingHistory.css";
import CustomerService from "../../services/CustomerService";
import AuthenticationService from "../../services/AuthenticationService";
import { Table } from 'reactstrap';

//for debug printing using util2.inspect(object, false, null, true)
const util2 = require('util');

class BookingHistory extends Component {

  // Change options to get from backend
  state = {
    bookings: []
  }

  componentWillMount() {
    CustomerService.getCustomerPastBookings(AuthenticationService.getLoggedInId()).then((response) => {
      this.setState({
        bookings: response.data
      })
    });
  }

  render() {
    let bookings = this.state.bookings.map((booking) => {
      return (
        <tr key={booking.id}>
          <td>{booking.id}</td>
          <td>-</td>
          <td>{booking.serviceTitle}</td>
          <td>{booking.date}</td>
          <td>{booking.startTime}</td>
          <td>{booking.endTime}</td>
          <td>{booking.workerName}</td>
        </tr>
      )
    });

    return (
      <div className="bookings">
        <header className="bookings-header">Booking History</header>
        <br></br><br></br>
        <Table>
          <thead>
            <tr>
              <th>#</th>
              <th>Business</th>
              <th>Service</th>
              <th>Date</th>
              <th>Start Time</th>
              <th>End Time</th>
              <th>Worker</th>
            </tr>
          </thead>          
          <tbody>
            {bookings}
          </tbody>          
        </Table>  
      </div>
    );
  }

}

export default BookingHistory;