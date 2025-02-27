import axios from "axios";
import API_HOST, {USER_NAME_SESSION_ATTRIBUTE_NAME, ROLE_SESSION_ATTRIBUTE, ID_SESSION_ATTRIBUTE, BUSINESS_ID_SESSION_ATTRIBUTE} from "../Utils/utils";

// Service class that stores the authenticated user variables
class AuthenticationService {

    executeBasicAuthenticationService(username, password) {
        // Authenicates User and passes username and password to backend
        return axios.get(`${API_HOST}/auth/${username}/${password}`,
            { headers: { authorization: this.createBasicAuthToken(username, password) } }
            );
    }

    createBasicAuthToken(username, password, id) {
        return 'Basic ' + window.btoa(username + ":" + password);
    }

    registerSuccessfulLogin(username, password, id) {
        // We only register successful login if the returned axios request is not an exception
        // i.e the user was found with the right creditentials in the database
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
        // From now on every request will have an authorisation token created
        // so that we don't have to create it each time we make a request to our server
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password));
    }

    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token;
                }
                return config;
            }
        )
    }

    isUserLoggedIn() {
        let retVal = true;
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        if (user === null){
            retVal = false;
        }
        return retVal;
    }

    getLoggedInUserName() {
        let retVal = ''
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        if (user !== null){
            retVal = user;
        }
        return retVal;
    }

    getLoggedInId() {
        let retVal = ''
        let id = sessionStorage.getItem(ID_SESSION_ATTRIBUTE)
        if (id !== null){
            retVal = id;
        }
        return retVal;
    }

    getRole() {
        let retVal = ''
        let role = sessionStorage.getItem(ROLE_SESSION_ATTRIBUTE)
        if (role !== null){
            retVal = role;
        }
        return retVal;
    }
    
    getBusinessId() {
        let retVal = ''
        let businessId = sessionStorage.getItem(BUSINESS_ID_SESSION_ATTRIBUTE)
        if (businessId !== null){
            retVal = businessId;
        }
        return retVal;
    }

    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(ROLE_SESSION_ATTRIBUTE);
        sessionStorage.removeItem(ID_SESSION_ATTRIBUTE);
        sessionStorage.removeItem(BUSINESS_ID_SESSION_ATTRIBUTE)
    }
}

export default new AuthenticationService()