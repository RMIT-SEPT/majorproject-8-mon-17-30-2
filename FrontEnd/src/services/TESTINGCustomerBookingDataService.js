import axios from 'axios'
//const API_URL = 'http://localhost:8080'
const API_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com'

class CustomerBookingDataService {
    // Grabs all bookings using backend API
    async retrieveCustomerBookings(username) {
        return axios.get(`${API_URL}/api/booking/customer/${username}`);
    }
}

export default new CustomerBookingDataService()
