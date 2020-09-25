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


    getWorkerByUsername(workerUsename){
        return axios.get(WORKER_API_BASE_URL + '/' + workerUsename);
    }

    getWorkSlotByDateAndWorkerId(workerId, date){
        return axios.get(WORKER_API_BASE_URL + '/' + workerId + '/work-slots/' + date);
    }

    getWorkersByBusiness(businessId){
        return axios.get(WORKER_API_BASE_URL + '/business/' + businessId);
    }



}

export default new WorkerService()
