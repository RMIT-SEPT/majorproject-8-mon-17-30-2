const API_HOST = 'http://localhost:8080'
const GET_CUSTOMER_URL = '/api/customer/';
const POST_CUSTOMER_URL= "api/customer/register";

const GET_ADMIN_URL = '/api/admin/';

const CUSTOMER = "CUSTOMER";
const ADMIN = "ADMIN";
const WORKER = "WORKER";


const CUSTOMER_BUTTON_DETAILS = [
    {key: 1, title: "Make a Booking", desc: "Make Bookings!"},
    {key: 2, title: "Manage Bookings", desc: "Edit or Cancel your current bookings!"},
    {key: 3, title: "Booking History", desc: "View successfully completed bookings"}
];

const ADMIN_BUTTON_DETAILS = [
{key: 1, title: "Manage Employees", desc: "View, Add or Edit employees!"},
{key: 2, title: "Manage Schedule", desc: "Add or Edit working times!"},
{key: 3, title: "Booking Summary", desc: "View a summary of completed bookings or ongoing bookings!"}
];



export default API_HOST;
export {GET_CUSTOMER_URL, GET_ADMIN_URL, POST_CUSTOMER_URL, CUSTOMER, ADMIN, WORKER, CUSTOMER_BUTTON_DETAILS, ADMIN_BUTTON_DETAILS};