import axios from 'axios';
import {WORKSLOT_API_BASE_URL, BOOKINGSLOT_API_BASE_URL} from '../Utils/utils';


class SlotService {
    //@PostMapping("/api/work-slot")
    addWorkSlot(workSlot){
        return axios.post(WORKSLOT_API_BASE_URL, workSlot);
    }
    //@GetMapping("/api/work-slot/{workSlotId}")
    getWorkSlotById(workSlotId){
        return axios.get(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }
    //@PostMapping("/api/work-slot/{workSlotId}/booking-slot")
    addBookingSlot(workSlotId, bookingSlot){
        return axios.post(WORKSLOT_API_BASE_URL + '/' + workSlotId + '/booking-slot', bookingSlot)
    }
    //@RequestMapping(method = RequestMethod.PUT, value = "/api/work-slot/{workSlotId}")
    editWorkSlot(workSlotId, workSlot){
        return axios.put(WORKSLOT_API_BASE_URL + '/' + workSlotId, workSlot);
    }
    //@RequestMapping(method = RequestMethod.PUT, value = "/api/booking-slot/{bookingSlotId}")
    editBookingSlot(bookingSlotId, bookingSlot){
        return axios.put(BOOKINGSLOT_API_BASE_URL + '/' + bookingSlotId, bookingSlot)
    }
    //@DeleteMapping(value = "/api/booking-slot/{bookingSlotId}")
    deleteBookingSlot(bookingSlotId){
        return axios.delete(BOOKINGSLOT_API_BASE_URL + '/' + bookingSlotId);
    }
    //@DeleteMapping(value = "/api/work-slot/{workSlotId}")
    deleteWorkSlotById(workSlotId){
        return axios.delete(WORKSLOT_API_BASE_URL + '/' + workSlotId);
    }
}

export default new SlotService()