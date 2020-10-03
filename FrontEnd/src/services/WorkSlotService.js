import axios from 'axios';
import API_HOST from '../Utils/utils';

const WORKSLOT_API_BASE_URL = API_HOST + '/api/work-slot'

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
    
    deleteWorkSlotById(workSlotId){
        return axios.delete(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }
}

export default new WorkerService()