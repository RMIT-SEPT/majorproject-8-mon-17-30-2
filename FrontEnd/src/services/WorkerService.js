import axios from 'axios';
import API_HOST from '../Utils/utils';

const WORKER_API_BASE_URL = API_HOST + '/api/worker'

class WorkerService {

    getAllWorkers(){
        return axios.get(WORKER_API_BASE_URL);
    }

    getWorkerById(workerId){
        return axios.get(WORKER_API_BASE_URL + '/' + workerId);
    }

    getWorkerByUsername(workerUsername){
        return axios.get(WORKER_API_BASE_URL + '/' + workerUsername);
    }

    getWorkSlotsByDateAndWorkerId(workerId, date){
        return axios.get(WORKER_API_BASE_URL + '/' + workerId + '/work-slots/' + date);
    }

    getWorkersByBusiness(businessId){
        return axios.get(WORKER_API_BASE_URL + '/business/' + businessId);
    }

    updateWorker(workerId, worker){
        return axios.put(WORKER_API_BASE_URL + '/edit/' + workerId, worker);
    }//worker/register

    addWorker(worker){
        return axios.post(WORKER_API_BASE_URL + '/register');
    }

}

export default new WorkerService()
