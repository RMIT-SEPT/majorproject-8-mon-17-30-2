import React, {Component} from "react";
import BookingBubble from "./BookingBubble.jsx";
import CustomerService from "../../services/CustomerService";
import AuthenticationService from "../../services/AuthenticationService";

//for debug printing using util2.inspect(object, false, null, true)
// const util2 = require('util');

class CurrentBookings extends Component {

  // Change options to get from backend
  state = {
    bookings: []
  }

  componentDidMount() {
    CustomerService.getCustomerCurrentBookings(AuthenticationService.getLoggedInId()).then((response) => {
      this.setState({
        bookings: response.data
      })
    });
  }

  render() {
    let bookings = <div className = "bookings-header">No Bookings Found</div>
    if(this.state.bookings.length > 0){
      bookings = this.state.bookings.map((booking) => {
        return <BookingBubble key = {booking.id} booking = {booking} />
      });
    }

    return (
      <div>
        <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> Bookings <span role="img" aria-label="Time emoji">🕒</span></header>
        {bookings}
      </div>

    );
  }

}

export default CurrentBookings;