import axios from 'axios'
import API_HOST from '../Utils/utils';

<<<<<<< HEAD
const BOOKING_API_BASE_URL = 'http://localhost:8080/api/booking'
// const BOOKING_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/booking'
=======
const BOOKING_API_BASE_URL = API_HOST + '/api/booking'
>>>>>>> develop

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

    getMatchingBookingSlots(searchRequest){
        return axios.post(API_HOST + '/api/booking-slot/search', searchRequest);
    }

    getBookingSlotById(bookingSlotId){
        return axios.get(API_HOST + '/api/booking-slot/' + bookingSlotId);
    }

    deleteCustomerBooking(bookingId){
        return axios.delete(BOOKING_API_BASE_URL  + '/' + bookingId);
    }

}

export default new BookingService()
