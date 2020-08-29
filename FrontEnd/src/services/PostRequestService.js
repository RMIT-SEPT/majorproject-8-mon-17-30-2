import axios from 'axios'
import API_HOST from "../utils/utils";

class PostRequestService {
    // Service for performing post request just need to input URL and body
    async postRequest(url, postedObject) {
        return axios.post(`${API_HOST}${url}`, postedObject);
    }   
}

export default new PostRequestService()