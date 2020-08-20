import React, {useState, useEffect} from 'react';
import CustomerBookingDataService from './CustomerBookingDataService';

function ListCustomerBookings(props){
    const [customerBookings, setCustomerBookings] = useState([]);

    useEffect( () => {
        getBookings();
    }, [])

    async function getBookings(){
        CustomerBookingDataService.retrieveCustomerBookings('Hardcoded')
        .then(response => {
            console.log(response);
            setCustomerBookings(response.data);
            
        });
    }

    return(
        <div className="container">
            <h3>All Bookings</h3>
            <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            customerBookings.map(
                                booking =>
                                    <tr key={booking.bookingId}>
                                        <td>{booking.bookingId}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )


}

export default ListCustomerBookings;