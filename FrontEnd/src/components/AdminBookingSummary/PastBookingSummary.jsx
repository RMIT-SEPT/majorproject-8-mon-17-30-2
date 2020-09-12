import React, {useState, useEffect} from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";

function PastBookingSummary(props){

    const [business, setBusiness] = useState();
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            setBusiness(response.data.businessId);
        }).catch((response) => console.log(response));




        BusinessService.getBusinessPastBookings(business).then(response => {
            console.log(response.data);
            setBookings(response.data)
        }).catch(console.log("ERROR OBTAINING PAST BOOKINGS"));


        
    }, []);

    return(
        <div>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
            { bookings.length > 0 ? 
                <h1> booking found </h1>
                :
                <div className = "bookings-header">No Bookings Found</div>
            }

        </div>
    );
}

export default PastBookingSummary;