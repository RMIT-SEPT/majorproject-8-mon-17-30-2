import axios from 'axios'
import API_HOST from '../Utils/utils';

const BOOKING_API_BASE_URL = 'http://localhost:8080/api/booking'

// service for all REST api calls stemming from the url 'api/booking'
class BookingService {   
    
    getAllBookings(){
        return axios.get(BOOKING_API_BASE_URL);
    }

    // // hypothetical api
    // getAllActiveBookings(){
    //     return axios.get(BOOKING_API_BASE_URL + '/active');
    // }

    addBooking(booking){
        return axios.post(BOOKING_API_BASE_URL, booking);
    }

    getNewestBookingSlot(){
        return axios.get(API_HOST + '/api/booking-slot/newest');
    }

}

export default new BookingService()