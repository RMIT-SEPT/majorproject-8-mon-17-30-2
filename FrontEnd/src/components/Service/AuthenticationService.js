import axios from "axios";
const API_URL = 'http://localhost:8080';
export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
export const ROLE_SESSION_ATTRIBUTE = "Role"
class AuthenticationService {
    executeBasicAuthenticationService(username, password) {
        return axios.get(`${API_URL}/auth/${username}/${password}`,
            { headers: { authorization: this.createBasicAuthToken(username, password) } }
            );
    }
    createBasicAuthToken(username, password) {
        return 'Basic ' + window.btoa(username + ":" + password)
    }

    registerSuccessfulLogin(username, password) {

        //axios request to get user and role if the user is not available do not set user?

        
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }
    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }
    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }
    getRole() {
        let role = sessionStorage.getItem(ROLE_SESSION_ATTRIBUTE)
        if (role === null) return ''
        return role
    }
    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(ROLE_SESSION_ATTRIBUTE);
    }
}


export default new AuthenticationService()