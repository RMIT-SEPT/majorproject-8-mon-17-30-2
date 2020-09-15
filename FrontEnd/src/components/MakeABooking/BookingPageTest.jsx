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
  const [customer, setCustomer] = useState(null);
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

  // modal logic
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
    
  useEffect(() => {

    CustomerService.getCustomerById(AuthenticationService.getLoggedInId())
    .then((response) =>{
      setCustomer(response.data)
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
    console.log("Setting serviceId to ", id);
    setServiceId(id);
  }

  function forceSetWorkerId(id){
    console.log("Setting workerId to ", id);
    setWorkerId(id);
  }

  function forceSetBookingSlots(data){
    console.log("Setting bookingSlots to ", data);
    setBookingSlots(data);
    console.log(bookingSlots);
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
    setBookingSlotId(bookingSlot_id);
    forceSetServiceId(service_id);
    handleChoose();
  }
  
  // clicking "BOOK" on a result card inside the modal (committing to creating a booking)
  function handleChoose() {

    BookingService.getBookingSlotById(bookingSlotId).then((response) => {
      setBookingSlot(response);
    });
    
    const newBooking = {
      customerId: {customerId},
      workerId: {workerId},
      serviceId: {serviceId},
      businessId: {businessId},
      bookingSlotId: {bookingSlotId}
    };

    console.log(newBooking);

    PostRequestService.postRequest("/api/booking", newBooking)
      .then((response) => {
        if (response.data != null) {
          alert("Booking Created");
        } else {
          alert("error");
        }
      })
      .catch(() => {
        
    });
  }

  let bookingSlotList = <p>No Bookings Found!</p>;  
  function handleModal(){
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
              onChoose={() => handleChosenSlot}
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