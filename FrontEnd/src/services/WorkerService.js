import axios from 'axios';
import API_HOST from '../Utils/utils';

const WORKER_API_BASE_URL = API_HOST + '/api/worker'

class WorkerService {
    //@GetMapping("/api/worker")
    getAllWorkers(){
        return axios.get(WORKER_API_BASE_URL);
    }
    //@GetMapping("/api/worker/{workerId}")
    getWorkerById(workerId){
        return axios.get(WORKER_API_BASE_URL + '/' + workerId);
    }
    //@GetMapping("/api/worker/{workerUsername}")
    getWorkerByUsername(workerUsername){
        return axios.get(WORKER_API_BASE_URL + '/' + workerUsername);
    }
    //@GetMapping("/api/worker/{workerId}/work-slots/{date}")
    getWorkSlotsByDateAndWorkerId(workerId, date){
        return axios.get(WORKER_API_BASE_URL + '/' + workerId + '/work-slots/' + date);
    }
    //@GetMapping("/api/worker/business/{businessId}")
    getWorkersByBusiness(businessId){
        return axios.get(WORKER_API_BASE_URL + '/business/' + businessId);
    }
    //@RequestMapping(method = RequestMethod.PUT, value = "/api/worker/edit/{id}")
    updateWorker(workerId, worker){
        return axios.put(WORKER_API_BASE_URL + '/edit/' + workerId, worker);
    }
    //@PostMapping("worker/register")
    addWorker(worker){
        return axios.post(WORKER_API_BASE_URL + '/register', worker);
    }
}

export default new WorkerService()
