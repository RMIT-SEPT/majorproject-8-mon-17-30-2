import axios from 'axios'
import API_HOST from '../Utils/utils';

class CustomerBookingDataService {
    // Grabs all bookings using backend API
    async retrieveCustomerBookings(username) {
        return axios.get(`${API_HOST}/api/booking/customer/${username}`);
    }
}

export default new CustomerBookingDataService()
