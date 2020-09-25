import React, {Component} from "react";
import BookingBubble from "../Bubbles/BookingBubble.jsx";
import BusinessService from "../../services/BusinessService";
import AuthenticationService from "../../services/AuthenticationService";

//for debug printing using util2.inspect(object, false, null, true)
// const util2 = require('util');

class ViewAvailabilty extends Component {

  state = {
    workSlots: []
  }

  componentDidMount() {
    BusinessService.getBusinessAvailability(1).then((response) => {
      this.setState({
        workSlots: response.data
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
        {/* <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> Booking History <span role="img" aria-label="Time emoji">🕒</span></header>
        {bookings} */}
        
      </div>

    );
  }

}

export default ViewAvailabilty;