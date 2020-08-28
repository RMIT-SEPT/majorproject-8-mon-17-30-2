import axios from 'axios'

const API_HOST = 'http://localhost:8080'


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
