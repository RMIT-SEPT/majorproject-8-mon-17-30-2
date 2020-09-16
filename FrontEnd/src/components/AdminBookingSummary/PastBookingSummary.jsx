import React, {useState, useEffect} from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import BookingBubble from "../BookingHistory/BookingBubble";

function PastBookingSummary(props){

    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            let businessId = response.data.businessId;
            getPastBookings(businessId);
        }).catch();
    }, []);

    function getPastBookings(businessId){
        BusinessService.getBusinessPastBookings(businessId).then(response => {
            setBookings(response.data)
        }).catch();
    }

    return(
        <div>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
            { bookings.length > 0 ? 
               bookings.map((booking) => <BookingBubble key = {booking.id} booking = {booking} />)
                :
                <div className = "bookings-header">No Bookings Found</div>
            }

        </div>
    );
}

export default PastBookingSummary;