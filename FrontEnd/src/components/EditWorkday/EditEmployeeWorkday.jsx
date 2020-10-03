import React, {useEffect, useState} from "react";
import AddWorkSlot from "./AddWorkSlot"
import EditWorkSlot from "./EditWorkSlot"
import AddBookingSlot from "./AddBookingSlot"
import EditBookingSlot from "./EditBookingSlot"
import WorkSlotsByDay from "./WorkSlotsByDay"
import "../../css/EditWorkday.css"
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import WorkerService from "../../services/WorkerService.js"
import { Button, Modal } from 'react-bootstrap';
import moment from 'moment';
import SlotService from "../../services/SlotService";
import ServiceService from "../../services/ServiceService.js";

// props = workerId
function EditEmployeeWorkday(props) {

    const todayString = moment().format('YYYY-MM-DD');
    const today = moment().toDate();
    const maxdate = moment().add(30,'days').toDate();

    const [worker, setWorker] = useState();
    const [workerName, setWorkerName] = useState();
    const [workerId] = useState(props.match.params.workerId)
    const [businessId, setBusinessId] = useState();

    // adding slots
    const [workSlots, setWorkSlots] = useState();
    const [currentWorkSlot, setCurrentWorkSlot] = useState();
    const [availableServices, setAvailableServices] = useState([]);

    // editing slots
    const [editingWorkSlot, setEditingWorkSlot] = useState(false);
    const [editingBookingSlot, setEditingBookingSlot] = useState(false);
    const [currentBookingSlot, setCurrentBookingSlot] = useState();

    const [date, setDate] = useState(today);
    const [dateString, setDateString] = useState(todayString);

    const [successfulDeletion, setSuccessfulDeletion] = useState();
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
        setEditingWorkSlot(false);
        setEditingBookingSlot(false);
    }

    function handleWorkSlotModal(){
        setSuccessfulDeletion(0);
        setShowWorkSlot(true);  
    }

    function handleBookingSlotModal(workSlotId){
        SlotService.getWorkSlotById(workSlotId)
        setSuccessfulDeletion(0);
        SlotService.getWorkSlotById(workSlotId)
        .then((response) =>{
            setCurrentWorkSlot(response.data);
        });  
        setShowBookingSlot(true);
    }

    function handleEditWorkSlot(workSlot){
        setCurrentWorkSlot(workSlot);
        setEditingWorkSlot(true);        
    }

    function handleEditBookingSlot(bookingSlot){
        setCurrentBookingSlot(bookingSlot);
        setEditingBookingSlot(true);        
    }

    // init and render update
    function initServices(){
        ServiceService.getServicesByBusinessId(businessId)
        .then((response) =>{
            setAvailableServices(response.data);
        });
    }

    useEffect(() =>{
        WorkerService.getWorkerById(workerId)
        .then((response) =>{
            setWorker(response.data);
            setWorkerName(response.data.name);
            setBusinessId(response.data.businessId);
        });
        initServices();
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            setWorkSlots(response.data.length ? response.data : []);
        });
    },[date, showWorkSlot, showBookingSlot,
       currentWorkSlot, currentBookingSlot,
       editingBookingSlot, editingWorkSlot]);

    // submitting
    function newWorkSlot(startTime, endTime){       
        const workSlot = {
            workerId: workerId,
            businessId: worker.businessId,
            date: dateString,
            startTime: startTime,
            endTime: endTime
        };
        SlotService.addWorkSlot(workSlot)
        .then(() =>{
            handleClose();
            alert("Workslot Created!");
        }).catch(() => {
            alert("Error: Workslot Overlap.\n" + workerName + " can't be in two places at once!\nTry a different time range.");
        });  
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            setWorkSlots(response.data.length ? response.data : []);
        });   
    }

    function newBookingSlot(startTime, endTime, workSlotId, services){        
        const bookingSlot = {
            workSlotId: workSlotId,
            date: dateString,
            startTime: startTime,
            endTime: endTime,
            serviceIds: services
        };
        SlotService.addBookingSlot(workSlotId, bookingSlot)
        .then(() =>{
            handleClose();
            alert("BookingSlot Created!");
        }).catch((error) => {
            alert(error.message + "Error: BookingSlot Overlap.\n" + workerName + " can't be in two places at once!\nTry a different time range.");
        });  
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            setWorkSlots(response.data.length ? response.data : []);
        });   
    }

    // editing
    function editWorkSlot(newStart, newEnd){
        const workSlot = {
            workerId: workerId,
            businessId: worker.businessId,
            date: dateString,
            startTime: newStart,
            endTime: newEnd
        }
        SlotService.editWorkSlot(currentWorkSlot.id, workSlot)
        .then(() =>{
            handleClose();
            alert("Workslot saved!");
        }).catch((error) => {
            alert(error.message);
        });  
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            setWorkSlots(response.data.length ? response.data : []);
        }); 
    }

    function editBookingSlot(bookingSlot){
        console.log("edited booking slot!", bookingSlot);
        var bookingSlotId = currentBookingSlot.id;
        SlotService.editBookingSlot(bookingSlotId, bookingSlot)
        .then(() =>{
            handleClose();
            alert("BookingSlot Created!");
        }).catch((error) => {
            alert(error.message);
        });  
        WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
        .then((response) =>{
            setWorkSlots(response.data.length ? response.data : []);
        });   
    }

    function deleteWorkSlot(workSlotId){
        SlotService.deleteWorkSlotById(workSlotId)
        .then((response) =>{
            setSuccessfulDeletion(response.status);
        }).catch((err) => {
            setSuccessfulDeletion(err.status);
        })
        .finally(() => {
            WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
            .then((response) =>{
                setWorkSlots(response.data.length ? response.data : []);
            });   
        });
    }

    function deleteBookingSlot(bookingSlotId){
        SlotService.deleteBookingSlot(bookingSlotId).then((response) =>{
          console.log(response);
          if(response.status === 200){
            setSuccessfulDeletion(response.status + "booking-slot");
          }
        })
        .catch()
        .finally(() => {
            WorkerService.getWorkSlotsByDateAndWorkerId(workerId, dateString)
            .then((response) =>{
                setWorkSlots(response.data.length ? response.data : []);
            });   
        });
    }

    return(
        <div className="container-fluid">
            <br/>
            <h1>Editing {workerName}'s Roster</h1>
            <br/>
            {successfulDeletion == 200 ? <div className="alert alert-success delete-workslot"> Successfully Deleted WorkSlot</div> : null}
            {successfulDeletion == "200booking-slot" ? <div className="alert alert-success delete-workslot"> Successfully Deleted BookingSlot</div> : null}
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

            <WorkSlotsByDay 
                className="workslot" 
                workerId={workerId} 
                date={dateString} 
                workSlots={workSlots} 
                addBookingSlot={handleBookingSlotModal}
                handleEditWorkSlot={handleEditWorkSlot}
                handleEditBookingSlot={handleEditBookingSlot}
                editWorkSlot={editWorkSlot}
                editBookingSlot={editBookingSlot}
                deleteWorkSlot={deleteWorkSlot}
                deleteBookingSlot = {deleteBookingSlot}
            />
            <Button className="addworkslot" onClick={handleWorkSlotModal}>+</Button>

            {/*add work slot*/}
            <Modal show={showWorkSlot} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title>Add a Workslot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddWorkSlot 
                        onSubmit={newWorkSlot} 
                        date={dateString}
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

            {/*add booking slot*/}
            <Modal show={showBookingSlot} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title>Add a BookingSlot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddBookingSlot 
                        workSlot={currentWorkSlot}
                        availableServices={availableServices}
                        onSubmit={newBookingSlot}
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

            {/*edit work slot*/}
            <Modal show={editingWorkSlot} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title>Edit Workslot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <EditWorkSlot 
                        workSlot={currentWorkSlot}
                        onSubmit={editWorkSlot}
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

            {/*edit booking slot*/}
            <Modal show={editingBookingSlot} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title>Edit Booking Slot</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <EditBookingSlot 
                        workSlot={currentWorkSlot}
                        bookingSlot={currentBookingSlot}
                        availableServices={availableServices}
                        onSubmit={editBookingSlot}
                    />
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