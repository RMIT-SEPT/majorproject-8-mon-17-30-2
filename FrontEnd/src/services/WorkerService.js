import axios from 'axios';

const WORKER_API_BASE_URL = 'http://milestone2application-env.eba-zp9wdxdp.us-east-1.elasticbeanstalk.com/api/worker'

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
