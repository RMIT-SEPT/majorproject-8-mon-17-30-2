import React, { useState, useEffect } from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import AvailabilityBubble from "../Bubbles/AvailabilityBubble";

/*
    Displays all available slots for a business using the availablityBubbles
*/
function ViewAvailabilty() {

    const [availSlots, setAvailSlots] = useState([]);
    //initalise workslots
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
            <header className="bookings-header">Available Worker Slots (Next 7-Days)</header>
            { availSlots.length > 0 ? 
               availSlots.map((availSlots) => <AvailabilityBubble key = {availSlots.id} booking = {availSlots} />) 
                :
                <h5 className="text-muted" style={{textAlign: 'center'}} >No Available Slots found!</h5>
            }
        </div>
    );
}

export default ViewAvailabilty;