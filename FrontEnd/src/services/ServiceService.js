import axios from 'axios';

const SERVICE_API_BASE_URL = 'http://localhost:8080/api/service'

class ServiceService {
    
    getServiceById(serviceId){
        return axios.get(SERVICE_API_BASE_URL + '/' + serviceId);
    } 

}

export default new ServiceService()