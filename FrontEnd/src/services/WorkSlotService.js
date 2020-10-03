import axios from 'axios';
import API_HOST from '../Utils/utils';

const WORKSLOT_API_BASE_URL = API_HOST + '/api/work-slot'
const BOOKINGSLOT_API_BASE_URL = API_HOST + '/api/booking-slot'

class WorkerService {

    addWorkSlot(workSlot){
        return axios.post(WORKSLOT_API_BASE_URL, workSlot);
    }

    getWorkSlotById(workSlotId){
        console.log("getting workslot ", workSlotId);
        return axios.get(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }

    addBookingSlot(workSlotId, bookingSlot){
        console.log("adding bookingslot", workSlotId, bookingSlot)
        return axios.post(WORKSLOT_API_BASE_URL + '/' + workSlotId + '/booking-slot', bookingSlot)
    }
    deleteBookingSlot(bookingSlotId){
        console.log("delete bookingslotId:", bookingSlotId)
        return axios.delete(BOOKINGSLOT_API_BASE_URL + '/' + bookingSlotId);
    }
    
    deleteWorkSlotById(workSlotId){
        return axios.delete(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }
}

export default new WorkerService()