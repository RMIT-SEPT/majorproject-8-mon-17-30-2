import axios from 'axios';
import API_HOST from '../Utils/utils';

const SERVICE_API_BASE_URL = API_HOST + '/api/service'

class ServiceService {

    getServiceById(serviceId){
        return axios.get(SERVICE_API_BASE_URL + '/' + serviceId);
    }

}

export default new ServiceService()
