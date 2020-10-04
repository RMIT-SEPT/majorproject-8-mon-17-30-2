import axios from 'axios';
import API_HOST from '../Utils/utils';

const BUSINESS_API_BASE_URL = API_HOST + '/api/business'

// service for all REST api calls stemming from the url 'api/customer'
class BusinessService {

    getAllBusinesses(){
        return axios.get(BUSINESS_API_BASE_URL);
    }

    getBusinessPastBookings(businessId){
        return axios.get(BUSINESS_API_BASE_URL + '/' + businessId + '/bookings/past');
    }

    getBusinessById(businessId){
        return axios.get(BUSINESS_API_BASE_URL + '/' + businessId);
    }

    getBusinessAvailability(businessId) {
        return axios.get(API_HOST + '/api/booking-slot/available/' + businessId);
    }

}

export default new BusinessService()
