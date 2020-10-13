import axios from 'axios'
import API_HOST, {BOOKING_API_BASE_URL} from '../Utils/utils';


// service for all REST api calls stemming from the url 'api/booking'
class BookingService {

    //@GetMapping("/api/booking")
    getAllBookings(){
        return axios.get(BOOKING_API_BASE_URL);
    }
    // @PostMapping("/api/booking")
    addBooking(booking){
        return axios.post(BOOKING_API_BASE_URL, booking);
    }
    // @GetMapping("/api/booking-slot/newest")
    getNewestBookingSlot(){
        return axios.get(API_HOST + '/api/booking-slot/newest');
    }
    //@PostMapping("/api/booking-slot/search")
    getMatchingBookingSlots(searchRequest){
        return axios.post(API_HOST + '/api/booking-slot/search', searchRequest);
    }
    //@GetMapping("/api/booking-slot/{id}")
    getBookingSlotById(bookingSlotId){
        return axios.get(API_HOST + '/api/booking-slot/' + bookingSlotId);
    }
    //@DeleteMapping("/api/booking/{bookingId}")
    deleteCustomerBooking(bookingId){
        return axios.delete(BOOKING_API_BASE_URL  + '/' + bookingId);
    }

}

export default new BookingService()
