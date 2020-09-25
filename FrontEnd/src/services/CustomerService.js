import axios from 'axios';
<<<<<<< HEAD
const CUSTOMER_API_BASE_URL = 'http://localhost:8080/api/customer'
// const CUSTOMER_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/customer'
=======
import API_HOST from '../Utils/utils';

const CUSTOMER_API_BASE_URL = API_HOST + '/api/customer'
>>>>>>> develop

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

    getCustomerPastBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings/past');
    }

    getCustomerCurrentBookings(customerId){
        return axios.get(CUSTOMER_API_BASE_URL + '/' + customerId + '/bookings/current');
    }

}

export default new CustomerService()
