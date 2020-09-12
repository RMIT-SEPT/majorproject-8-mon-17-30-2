import React, {useState, useEffect} from "react";

function PastBookingSummary(props){

    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        
    }, []);

    return(
        <div>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
        </div>
    );
}

export default PastBookingSummary;