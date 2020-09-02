import axios from 'axios';

const CUSTOMER_API_BASE_URL = 'http://localhost:8080/api/customer'

// service for all REST api calls stemming from the url 'api/customer'
class CustomerService {
    
    getAllCustomers(){
        return axios.get(CUSTOMER_API_BASE_URL);
    }

    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL, customer);
    }

    // need to setup security such that only the owner of this id (or an admin) can access
    getCustomerById(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId);
    }

    getCustomerBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings');
    }

    //all bookings for now
    getCustomerPastBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings/past');
    }

}

export default new CustomerService()