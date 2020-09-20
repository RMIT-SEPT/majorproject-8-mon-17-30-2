import axios from 'axios';

const WORKER_API_BASE_URL = 'http://localhost:8080/api/worker'

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

}

export default new WorkerService()