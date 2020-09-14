import axios from 'axios';

const BUSINESS_API_BASE_URL = 'http://localhost:8080/api/business'

// service for all REST api calls stemming from the url 'api/customer'
class BusinessService {
    
    getAllBusinesses(){
        return axios.get(BUSINESS_API_BASE_URL);
    }

}
export default new BusinessService()