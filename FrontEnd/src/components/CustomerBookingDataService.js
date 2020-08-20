import axios from 'axios'

const USERNAME = 'aus'
const PASSWORD = 'pass'
const BOOKING_API_URL = 'http://localhost:8080'
const FULL_BOOKING_API_URL = `${BOOKING_API_URL}/api/booking/customer/${USERNAME}`

class CustomerBookingDataService {

    retrieveCustomerBookings(name) {
        //console.log('executed service')
        return axios.get(`${FULL_BOOKING_API_URL}`,
            { headers: { authorization: 'Basic ' + window.btoa(USERNAME + ":" + PASSWORD) } }
        );
    }
}

export default new CustomerBookingDataService()
