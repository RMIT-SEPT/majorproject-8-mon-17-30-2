import axios from 'axios';
import API_HOST, {BUSINESS_API_BASE_URL} from '../Utils/utils';


// service for all REST api calls stemming from the url 'api/customer'
class BusinessService {
    //@GetMapping("/api/business")
    getAllBusinesses(){
        return axios.get(BUSINESS_API_BASE_URL);
    }
    //@GetMapping("/api/business/{businessId}/bookings/past")
    getBusinessPastBookings(businessId){
        return axios.get(BUSINESS_API_BASE_URL + '/' + businessId + '/bookings/past');
    }
    //@GetMapping("/api/business/{businessId}/bookings/new")
    getBusinessNewBookings(businessId){
        return axios.get(BUSINESS_API_BASE_URL + '/' + businessId + '/bookings/new');
    }
    //@GetMapping("/api/business/{businessId}")
    getBusinessById(businessId){
        return axios.get(BUSINESS_API_BASE_URL + '/' + businessId);
    }
    //@GetMapping("/api/booking-slot/available")
    getBusinessAvailability(businessId){
        return axios.get(API_HOST + '/api/booking-slot/available/' + businessId);
        // Change lines to DEBUG "available booking slots 7 days", below displays ALL Available BookingSlots, above has date and business constaint
        // return axios.get(API_HOST + '/api/booking-slot/available');
    }
    //@PostMapping("/api/admin")
    signUpAdmin(admin){
        return axios.post(API_HOST + '/api/admin', admin);
    }
    //@PostMapping("api/business/{adminId}")
    signUpBusiness(adminId, business){
        return axios.post(API_HOST + '/api/business/' + adminId, business);
    }

}

export default new BusinessService()
