import axios from 'axios';
<<<<<<< HEAD
const SERVICE_API_BASE_URL = 'http://localhost:8080/api/service'
// const SERVICE_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/service'
=======
import API_HOST from '../Utils/utils';

const SERVICE_API_BASE_URL = API_HOST + '/api/service'
>>>>>>> develop

class ServiceService {

    getServiceById(serviceId){
        return axios.get(SERVICE_API_BASE_URL + '/' + serviceId);
    }

}

export default new ServiceService()
