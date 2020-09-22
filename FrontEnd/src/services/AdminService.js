import axios from 'axios';

const ADMIN_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/admin'

// service for all REST api calls stemming from the url 'api/customer'
class AdminService {

    // need to setup security such that only the owner of this id (or an admin) can access
    getAdminById(adminId){
        return axios.get(ADMIN_API_BASE_URL + '/' + adminId);
    }



}

export default new AdminService()
