const API_HOST = 'http://localhost:8080'
// const API_HOST = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com'
const GET_CUSTOMER_URL = '/api/customer/';
const POST_CUSTOMER_URL= "/api/customer/register/";

const GET_ADMIN_URL = '/api/admin/';
const GET_WORKER_URL = '/api/worker/';

const CUSTOMER = "CUSTOMER";
const ADMIN = "ADMIN";
const WORKER = "WORKER";


const CUSTOMER_BUTTON_DETAILS = [
    {key: 1, title: "Make a Booking", desc: "Make Bookings!", link:"/bookings/business"},
    {key: 2, title: "Manage Bookings", desc: "Edit or Cancel your current bookings!", link:"/bookings/manage"},
    {key: 3, title: "Booking History", desc: "View successfully completed bookings", link:"/bookings/past"}
];

const ADMIN_BUTTON_DETAILS = [
    {key: 1, title: "Manage Employees", desc: "View, Add or Edit employees!", link:"/workers"},
    {key: 2, title: "Manage Schedule", desc: "Add or Edit working times!", link:"/schedule"},
    {key: 3, title: "Booking Summary", desc: "View a summary of completed bookings or ongoing bookings!", link:"/bookings/summary"},
    {key: 4, title: "View Worker's Availability", desc: "View when a worker is available for a Job in the upcoming 7 days!", link:"/workers/availability"}
];

const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
const ROLE_SESSION_ATTRIBUTE = "Role"
const ID_SESSION_ATTRIBUTE   = "Id"
const BUSINESS_ID_SESSION_ATTRIBUTE = "businessId"

const BUSINESS_CARD_LINK = "/bookings/create/";

export default API_HOST;
export {GET_CUSTOMER_URL, GET_ADMIN_URL, POST_CUSTOMER_URL, CUSTOMER, ADMIN, WORKER};
export {CUSTOMER_BUTTON_DETAILS, ADMIN_BUTTON_DETAILS, USER_NAME_SESSION_ATTRIBUTE_NAME};
export {ROLE_SESSION_ATTRIBUTE, ID_SESSION_ATTRIBUTE, GET_WORKER_URL, BUSINESS_CARD_LINK, BUSINESS_ID_SESSION_ATTRIBUTE};
