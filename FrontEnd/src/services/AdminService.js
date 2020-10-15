import axios from 'axios';
import {ADMIN_API_BASE_URL} from '../Utils/utils'


// service for all REST api calls stemming from the url 'api/admin'
class AdminService {
    // @GetMapping("/api/admin/{adminId}")
    getAdminById(adminId){
        return axios.get(ADMIN_API_BASE_URL + '/' + adminId);
    }

}

export default new AdminService()
