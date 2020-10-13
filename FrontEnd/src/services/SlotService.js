import axios from 'axios';
import {WORKSLOT_API_BASE_URL, BOOKINGSLOT_API_BASE_URL} from '../Utils/utils';


class SlotService {

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
        return axios.put(WORKSLOT_API_BASE_URL + '/' + workSlotId, workSlot);
    }

    editBookingSlot(bookingSlotId, bookingSlot){
        return axios.put(BOOKINGSLOT_API_BASE_URL + '/' + bookingSlotId, bookingSlot)
    }

    deleteBookingSlot(bookingSlotId){
        return axios.delete(BOOKINGSLOT_API_BASE_URL + '/' + bookingSlotId);
    }
    
    deleteWorkSlotById(workSlotId){
        return axios.delete(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }
}

export default new SlotService()