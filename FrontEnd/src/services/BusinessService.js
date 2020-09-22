import axios from 'axios';
// const BUSINESS_API_BASE_URL = 'http://localhost:8080/api/business'

const BUSINESS_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/business'

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

}

export default new BusinessService()
