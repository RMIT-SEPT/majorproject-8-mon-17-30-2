import React, {useEffect, useState} from "react";
import AddEmployeeButton from "./AddEmployeeButton";
import WorkerService from "../../services/WorkerService";
import AuthenticationService from "../../services/AuthenticationService";
import EmployeeListItem from "./EmployeeListItem";

function EmployeeList(){

    const [workers, setworkers] = useState([]);
    useEffect(()=>{
        WorkerService.getWorkersByBusiness(AuthenticationService.getBusinessId()).then(response => {
            setworkers(response.data);
        }).catch();

    },[]);


    return(
        <div className="card list list-width">
            <ul className="list-group list-group-flush remove-list-bullet">
            {workers.map(worker => {
                return(<EmployeeListItem key={worker.id} id={worker.id} name={worker.name}/>)
            })}
            
                <AddEmployeeButton />
            </ul>

      </div>
    );
}
export default EmployeeList;