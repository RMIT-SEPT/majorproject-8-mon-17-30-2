import React, {Component} from "react";
import BookingBubble from "../Bubbles/BookingBubble.jsx";
import CustomerService from "../../services/CustomerService";
import AuthenticationService from "../../services/AuthenticationService";

//for debug printing using util2.inspect(object, false, null, true)
// const util2 = require('util');

/*
    Page to display past bookings for a customer utilises booking bubbles view
*/
class BookingHistory extends Component {
  // Change options to get from backend
  state = {
    bookings: []
  }
//load past bookings
  componentDidMount() {
    CustomerService.getCustomerPastBookings(AuthenticationService.getLoggedInId()).then((response) => {
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
        <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> Booking History <span role="img" aria-label="Time emoji">🕒</span></header>
        {bookings}
      </div>

    );
  }

}

export default BookingHistory;