import axios from 'axios';
import API_HOST from '../Utils/utils';

const WORKSLOT_API_BASE_URL = API_HOST + '/api/work-slot'

class WorkerService {

    addWorkSlot(workSlot){
        return axios.post(WORKSLOT_API_BASE_URL, workSlot);
    }

    getWorkSlotById(workSlotId){
        return axios.get(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }

    addBookingSlot(workSlotId, bookingSlot){
        return axios.post(WORKSLOT_API_BASE_URL + '/' + workSlotId + '/booking-slot', bookingSlot)
    }

    editWorkSlot(workSlotId, workSlot){
        console.log(workSlotId, workSlot);
        return axios.put(WORKSLOT_API_BASE_URL + '/' + workSlotId, workSlot);
    }

    editBookingSlot(workSlotId, bookingSlotId, bookingSlot){
        return axios.put(WORKSLOT_API_BASE_URL + '/' + workSlotId + '/booking-slot/' + bookingSlotId, bookingSlot)
    }

}

export default new WorkerService()