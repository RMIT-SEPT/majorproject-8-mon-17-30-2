import axios from 'axios'
import API_HOST from "../Utils/utils";

class GetRequestService {
    // Generic Service for any get request
    // for example:
    // url = "api/booking/customer/"

    async getRequest(url) {
        return axios.get(`${API_HOST}${url}`);
    }
    async getRequestUsername(url, username) {
        return axios.get(`${API_HOST}${url}${username}`);
    }
    async getRequestId(url, id){
        return axios.get(`${API_HOST}${url}${id}`);
    }

}

export default new GetRequestService()