import axios from 'axios';
import {CUSTOMER_API_BASE_URL} from '../Utils/utils';


// service for all REST api calls stemming from the url 'api/customer'
class CustomerService {
    //@GetMapping("/api/customer")
    getAllCustomers(){
        return axios.get(CUSTOMER_API_BASE_URL);
    }
    //@PostMapping("customer/register")
    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL, customer);
    }

    //@GetMapping("/api/customer/{customerId}")
    // need to setup security such that only the owner of this id (or an admin) can access
    getCustomerById(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId);
    }
   //@GetMapping("/api/customer/{customerId}/bookings")
    getCustomerBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings');
    }
     //@GetMapping("/api/customer/{customerId}/bookings/past")
    getCustomerPastBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings/past');
    }
    //@GetMapping("/api/customer/{customerId}/bookings/current")
    getCustomerCurrentBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings/current');
    }
    //	@RequestMapping(method = RequestMethod.PUT, value = "/api/customer/{id}")
    updateCustomer(customerId, customer){
        return axios.put(CUSTOMER_API_BASE_URL + '/' + customerId, customer);
    }

}

export default new CustomerService()
