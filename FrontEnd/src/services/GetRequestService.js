import axios from 'axios'
import API_HOST from "../Utils/utils";

class GetRequestService {
    // Grabs all bookings using backend API
    // one function for inputting a username
    // for example
    // url = "api/booking/customer/"
    //
    async getRequest(url) {
        return axios.get(`${API_HOST}${url}`);
    }
    async getRequestUsername(url, username) {
        return axios.get(`${API_HOST}${url}${username}`);
    }

}

export default new GetRequestService()
