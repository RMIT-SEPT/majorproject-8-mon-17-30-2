// import React, {Component} from "react";
// import BookingBubble from "../Bubbles/BookingBubble.jsx";
// import BusinessService from "../../services/BusinessService";
// import AuthenticationService from "../../services/AuthenticationService";

// //for debug printing using util2.inspect(object, false, null, true)
// // const util2 = require('util');

// class ViewAvailabilty extends Component {

//   state = {
//     bookings: []
//   }

  
//   componentDidMount() {
//     BusinessService.getBusinessAvailability(AuthenticationService.getBusinessId()).then((response) => {
//       this.setState({
//         bookings: response.data
//       })
//     });
//   }

//   render() {
//     // let bookings = <div className = "bookings-header">No Bookings Found</div>
//     // if(this.state.bookings.length > 0){
//     //   bookings = this.state.bookings.map((booking) => {
//     //     return <BookingBubble key = {booking.id} booking = {booking} />
//     //   });
//     // }

//     return (
//       <div>
//         {/* <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> Booking History <span role="img" aria-label="Time emoji">🕒</span></header>
//         {bookings} */}
//       </div>

//     );
//   }

// }

// export default ViewAvailabilty;

import React, {useState, useEffect} from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import BookingBubble from "../Bubbles/BookingBubble";

function ViewAvailabilty(props){

    const [availSlots, setAvailSlots] = useState([]);

    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            // let businessId = response.data.businessId;
            getAvailiableWorkSlots();
        }).catch();
    }, []);

    function getAvailiableWorkSlots(){
        BusinessService.getBusinessAvailability(AuthenticationService.getBusinessId()).then(response => {
            setAvailSlots(response.data)
        }).catch();
    }

    return(
        <div>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">Available WorkSlot</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
            { availSlots.length > 0 ? 
               availSlots.map((booking) => <BookingBubble key = {booking.id} booking = {booking} />)
                :
                <div className = "bookings-header">No Bookings Found</div>
            }

        </div>
    );
}

export default ViewAvailabilty;