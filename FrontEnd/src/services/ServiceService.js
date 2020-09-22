import axios from 'axios';
//const SERVICE_API_BASE_URL = 'http://localhost:8080/api/service'
const SERVICE_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/service'

class ServiceService {

    getServiceById(serviceId){
        return axios.get(SERVICE_API_BASE_URL + '/' + serviceId);
    }

}

export default new ServiceService()
