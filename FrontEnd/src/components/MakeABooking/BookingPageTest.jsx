import React, { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AuthenticationService from "../../services/AuthenticationService";
import PostRequestService from "../../services/PostRequestService";
import GetRequestService from "../../services/GetRequestService";
import moment from "moment";
import CustomerService from "../../services/CustomerService";
import BusinessService from "../../services/BusinessService";
import WorkerService from "../../services/WorkerService";
import ServiceService from "../../services/ServiceService";
import BookingService from "../../services/BookingService";
import BookingSlotBubble from "../Bubbles/BookingSlotBubble";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";


function BookingPageTest(props) {

  // business variables
  const [business, setBusiness] = useState({
    id: "",
    name: "",
    services: [],
    workers: []
  });
  const [businessId, setBusinessId] = useState(null);
  const [customerId, setCustomerId] = useState(null);
  const [service, setService] = useState(null);
  const [serviceId, setServiceId] = useState(null);
  const [services, setServices] = useState([]);
  const [worker, setWorker] = useState(null);
  const [workerId, setWorkerId] = useState(null);
  const [workers, setWorkers] = useState([]);
  const [bookingSlot, setBookingSlot] = useState(null);
  const [bookingSlotId, setBookingSlotId] = useState(null);
  const [date, setDate] = useState(null);
  const [dateString, setDateString] = useState("");
  const [bookingSlots, setBookingSlots] = useState([]);

  var bookingSlot2 = null;
  var bookingSlotId2 = null;
  var serviceId2 = null;

  // modal logic
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
    
  useEffect(() => {

    CustomerService.getCustomerById(AuthenticationService.getLoggedInId())
    .then((response) =>{
      setCustomerId(response.data.id)
    });

    BusinessService.getBusinessById(props.match.params.businessId)
    .then((response) =>{
      setBusiness(response.data);
      setBusinessId(response.data.id)
      setServices(response.data.services);
      setWorkers(response.data.workers);
    });

  }, [] )

  function forceSetServiceId(id){
    setServiceId(id);
  }

  function forceSetWorkerId(id){
    setWorkerId(id);
  }

  function forceSetBookingSlotId(id){
    setBookingSlotId(id);
  }

  function forceSetBookingSlots(data){
    setBookingSlots(data);
  }

  function handleChange(event) {
    const {name, value} = event.target;
    if(name === "service"){
      forceSetServiceId(value);
    }
    if(name === "worker"){
      forceSetWorkerId(value);
    }
  }  

  function handleDate(chosenDate) {   
    if(chosenDate === null){
      setDate("");
      setDateString("");
    }
    else{
      var convertedDate = moment(chosenDate).format("YYYY-MM-DD");
      setDate(chosenDate);
      setDateString(convertedDate);
    }
  }

  var serviceList = <option value="" disabled hidden></option>;
  serviceList = services.map((service) => {
    return <option key={service.id} value={service.id}>{service.title}</option>
  });

  var workerList = <option value="" disabled hidden></option>;
  workerList = workers.map((worker) => {
    return <option key={worker.id} value={worker.id}>{worker.name}</option>
  });

  function handleChosenSlot(bookingSlot_id, service_id){
    console.log("incoming", bookingSlot_id, service_id);
    bookingSlotId2 = bookingSlot_id;
    serviceId2 = service_id;
    forceSetBookingSlotId(bookingSlot_id);
    forceSetServiceId(service_id);    
    console.log("now", bookingSlotId, serviceId)
    handleChoose();
  }
  
  // CREATE BOOKING by clicking "BOOK" on a result card inside the modal
  function handleChoose() {    
    BookingService.getBookingSlotById(bookingSlotId2)
      .then((response) => {
        console.log("api returned ", response.data);
        const newBooking = {
          customerId: customerId,
          workerId: response.data.workerId,
          businessId: response.data.businessId,
          bookingSlotId: response.data.id,
          serviceId: serviceId2
        }
        submitBooking(newBooking);
    });    
  }

  function submitBooking(booking){
    PostRequestService.postRequest("/api/booking", booking)
      .then((response) => {
        if (response.data != null) {
          alert("Booking Created");
        } else {
          alert("error");
        }
      })
      .catch(() => {        
    });
    handleClose();
  }

  let bookingSlotList = <p>No Bookings Found!</p>;  
  function handleModal(event){
    event.preventDefault();
    const searchRequest = {
      businessId: businessId,
      serviceId: serviceId,
      workerId: workerId,
      dateString: dateString
    }
    console.log(searchRequest);
    BookingService.getMatchingBookingSlots(searchRequest)
      .then((response) => {
        if(Array.isArray(response.data)){
          forceSetBookingSlots(response.data);
        }
        else{
          forceSetBookingSlots([]);
        }
    });
    setShow(true);  
  }
 
  return (   
     
  <>     
    <div className="form">
      <h1>Create a booking at {business.name}:</h1>
      <br/>
      <form onSubmit={handleModal}>

        <h4>Service</h4>
        <select
          style={{width: 150}}
          name="service"
          value={service}
          onChange={handleChange}>
          <option key="0" value="0">-Any-</option>
          {serviceList}
        </select>
        <br/><br/>

        <h4>Worker</h4>
        <select
          style={{width: 150}}
          name="worker"
          value={worker}
          onChange={handleChange}>
          <option key="0" value="0">-Any-</option>
          {workerList}
        </select>
        <br/><br/>
        
        <h4>Date</h4>
        <DatePicker
          name="date"
          selected={date}
          onChange={handleDate}
          isClearable
          dateFormat="yyyy/MM/dd"
          placeholderText="No Date Specified"
        />
        <br/><br/>

        <div>
          <input type="button" onClick={handleModal} value="Search for Bookings" />
        </div>
      </form>
    </div>

    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Choose a Booking</Modal.Title>
      </Modal.Header>
      <Modal.Body>
          {bookingSlots.map((bookingSlot) => (
            <BookingSlotBubble 
              bookingSlot={bookingSlot} 
              handleChosenSlot={(bookingSlot_id, service_id) => handleChosenSlot(bookingSlot_id, service_id)}
            />))
          }
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>

  </>

  );
  
}

export default BookingPageTest;