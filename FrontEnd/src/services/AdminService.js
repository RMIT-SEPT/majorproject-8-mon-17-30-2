import axios from 'axios';
import {ADMIN_API_BASE_URL} from '../Utils/utils'


// service for all REST api calls stemming from the url 'api/customer'
class AdminService {

    // need to setup security such that only the owner of this id (or an admin) can access
    getAdminById(adminId){
        return axios.get(ADMIN_API_BASE_URL + '/' + adminId);
    }



}

export default new AdminService()
