import axios from 'axios'

const API_URL = 'http://localhost:8080'

class CustomerBookingDataService {
    // Grabs all bookings using backend API
    async retrieveCustomerBookings(username) {
        return axios.get(`${API_URL}/api/booking/customer/${username}`);
    }
}

export default new CustomerBookingDataService()
