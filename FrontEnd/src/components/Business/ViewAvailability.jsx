import React, { useState, useEffect } from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import BookingBubble from "../Bubbles/BookingBubble";

function ViewAvailabilty(props) {

    const [availSlots, setAvailSlots] = useState([]);

    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            getAvailiableWorkSlots();
        }).catch();
    }, []);

    function getAvailiableWorkSlots() {
        BusinessService.getBusinessAvailability(AuthenticationService.getBusinessId()).then(response => {
            setAvailSlots(response.data)
        }).catch();
    }

    return (
        <div>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">All Available Worker Slots</span> </header>
            {/* { availSlots.length > 0 ?
               availSlots.map((booking) => <BookingBubble key = {booking.id} booking = {booking} />)
                :
                <div className = "bookings-header">No Bookings Found</div>
            } */}
            <ul>
                {availSlots.map(availSlots => (
                    <div key={availSlots.id}>{availSlots.businessName}{availSlots.date}</div>
                ))}
            </ul>
        </div>
    );
}

export default ViewAvailabilty;