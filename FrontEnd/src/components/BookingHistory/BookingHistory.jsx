import React, {Component} from "react";
import BookingBubble from "./BookingBubble.jsx";
import CustomerService from "../../services/CustomerService";
import AuthenticationService from "../../services/AuthenticationService";

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
      return BookingBubble(booking);
    });

    return (
      <div>
        <header className="bookings-header">🕒 Booking History 🕒</header>
        {bookings}
      </div>

    );
  }

}

export default BookingHistory;