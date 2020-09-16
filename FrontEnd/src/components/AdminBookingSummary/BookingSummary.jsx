import React from "react";
import DashboardCard from "../Dashboard/DashboardCard";
import "../../css/BookingSummary.css"
function BookingSummary(){


    return(
        <div className="booking-summary-heading">
            <h1>Booking Summary</h1>
            <div className="card-deck booking-summary-deck">
                <DashboardCard title="Past Bookings" desc="View a summary of past bookings!" link= "/bookings/summary/past"/>
                <DashboardCard title="New Bookings" desc="View a summary of new bookings!" link= "/bookings/summary/new"/>
            </div>
        </div>
    );

}

export default BookingSummary;