import axios from 'axios'
<<<<<<< HEAD
const API_URL = 'http://localhost:8080'
// const API_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com'
=======
import API_HOST from '../Utils/utils';
>>>>>>> develop

class CustomerBookingDataService {
    // Grabs all bookings using backend API
    async retrieveCustomerBookings(username) {
        return axios.get(`${API_HOST}/api/booking/customer/${username}`);
    }
}

export default new CustomerBookingDataService()
