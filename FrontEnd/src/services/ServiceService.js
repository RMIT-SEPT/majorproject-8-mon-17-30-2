import axios from 'axios';
import API_HOST, {SERVICE_API_BASE_URL} from '../Utils/utils';


class ServiceService {
    //@GetMapping("/api/service/{serviceId}")
    getServiceById(serviceId){
        return axios.get(SERVICE_API_BASE_URL + '/' + serviceId);
    }
    //@GetMapping("/api/business/{businessId}/services")
    getServicesByBusinessId(businessId){
        return axios.get(API_HOST + '/api/business/' + businessId + '/services')
    }

}

export default new ServiceService()
