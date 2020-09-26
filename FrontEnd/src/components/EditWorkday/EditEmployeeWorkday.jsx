import React, {useEffect, useState} from "react";
import AddWorkSlot from "./AddWorkSlot"
import WorkSlotsByDay from "./WorkSlotsByDay"
import "../../css/EditWorkday.css"
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import WorkerService from "../../services/WorkerService.js"
import { Button, Modal } from 'react-bootstrap';
import moment from 'moment';
import WorkSlotService from "../../services/WorkSlotService";

// props = workerId
function EditEmployeeWorkday(props) {

    const todayString = moment().format('YYYY-MM-DD');
    const today = moment().toDate();
    const maxdate = moment().add(30,'days').toDate();

    const [worker, setWorker] = useState();
    const [workerName, setWorkerName] = useState();
    const [workerId, setWorkerId] = useState(props.match.params.workerId)

    // adding slots
    const [workSlots, setWorkSlots] = useState();
    const [currentWorkSlot, setCurrentWorkSlot] = useState();

    const [date, setDate] = useState(today);
    const [dateString, setDateString] = useState(todayString);
    function handleDate(date){
        setDate(date);
        setDateString(moment(date).format('YYYY-MM-DD'));
    }

    // modal logic
    const [showWorkSlot, setShowWorkSlot] = useState(false);
    const [showBookingSlot, setShowBookingSlot] = useState(false);
    function handleClose(){
        setShowWorkSlot(false); 
        setShowBookingSlot(false);
    }
    function handleWorkSlotModal(){
        setShowWorkSlot(true);  
    }
    function handleBookingSlotModal(workSlotId){
        setCurrentWorkSlot(workSlotId);
        setShowBookingSlot(true);  
    }

    useEffect(() =>{
        WorkerService.getWorkerById(workerId)
        .then((response) =>{
            setWorker(response.data);
            setWorkerName(response.data.name);
        });
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            // console.log("response: ", response);
            setWorkSlots(response.data.length ? response.data : []);
        });
    },[date, showWorkSlot]);

    function newWorkSlot(startTime, endTime){
        console.log("ADDING NEW WORKSLOT");
        const workSlot = {
            workerId: workerId,
            businessId: worker.businessId,
            date: dateString,
            startTime: startTime,
            endTime: endTime
        };
        WorkSlotService.addWorkSlot(workSlot)
        .then(() =>{
            handleClose();
            alert("Workslot Created!");
        }).catch(() => {
            alert("Error: Workslot Overlap.\n" + workerName + " can't be in two places at once!\nTry a different time range.");
        });  
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            console.log("response: ", response);
            setWorkSlots(response.data.length ? response.data : []);
        });   
    }

    return(
        <div className="container-fluid">
            <br/>
            <h1>Editing {workerName}'s Roster</h1>
            <br/>
            <h4>For Date:</h4>
            <DatePicker
                selected={date}
                onChange={handleDate}
                dateFormat="yyyy-MM-dd"
                minDate={today}
                maxDate={maxdate}
                placeholderText="Choose a Date to Edit"
                showDisabledMonthNavigation
            />

            <br/><br/>

            <WorkSlotsByDay className="workslot" workerId={workerId} date={dateString} 
            workSlots={workSlots} addBookingSlot={(workSlotId) => handleBookingSlotModal(workSlotId)}/>
  

            <Button className="addworkslot" onClick={handleWorkSlotModal}>+</Button>

            <Modal show={showWorkSlot} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add a Workslot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddWorkSlot onSubmit={newWorkSlot} date={dateString}/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={showBookingSlot} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add a BookingSlot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Hey
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

        </div>
        
    );

}
export default EditEmployeeWorkday;