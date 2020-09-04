import React, { useState, useEffect, Component } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AuthenticationService from "../services/AuthenticationService";
import PostRequestService from "../services/PostRequestService";
import moment from "moment";
import CustomerService from "../services/CustomerService";
import WorkerService from "../services/WorkerService";

function BookingPage() {
    const [customer, setCustomer] = useState({
        id: "",
        name: "",
        username: "",
        address: "",
        email: "",
        phoneNumber: ""
        
    });


    const [booking, setBooking] = useState({
        cust:"",
        service: "",
        worker: "",
        date: "",
        time: "",
    });

    const [worker, setWorker] = useState({
        id: "",
        name: "",
        username: "",
        address: "",
        email: "",
        phoneNumber: ""
    });

    
    useEffect(() =>{
        CustomerService.getCustomerById(AuthenticationService.getLoggedInId()).then((response) =>{
            setCustomer({
                id: response.data.id,
                name: response.data.name,
                username: response.data.username,
                address: response.data.address,
                email: response.data.email,
                phoneNumber: response.data.phoneNumber
            });
        });

    },[]);
  

  function handleChange(event) {
        const {name, value} = event.target;
        if(name === "worker"){
            WorkerService.getWorkerById(value).then((response) => {
                setWorker({
                    id: response.data.id,
                    name: response.data.name,
                    username: response.data.username,
                    address: response.data.address,
                    email: response.data.email,
                    phoneNumber: response.data.phoneNumber
                });
            });

        }

        setBooking((prevValue) =>{
            return({
                ...prevValue,
                [name]: value
            });
        })
    }    
  

  

  function handleDate(date) {
    
        setBooking((prevValue) =>{
            return({
                ...prevValue,
                ["date"]: date
            });
        });
  }
 


  function handleSubmit(event) {

    event.preventDefault();
    let convertedDate = moment(booking.date).format("YYYY-MM-DD");
    const newBooking = {
      customer: customer,
      worker: worker,
      service: {
        title: booking.service,
      },
      business: {
        businessName: "",
      },
      bookingSlot: {
        date: convertedDate,
        startTime: "14:30:00",
        endTime: "15:00:00",
      },
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

 
    return (
      <div className="form">
        <form onSubmit={handleSubmit}>
          <h4>Service</h4>
          <select required="true"
            name="service"
            onChange={handleChange}
          >
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option value="Haircut">Haircut</option>
            <option value="BeardTrim">BeardTrim</option>
          </select> 

          <h4>Worker</h4>
          <select
            name="worker"
            onChange={handleChange}
          >
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option value="1">John</option>
          </select>

          <h4>Date</h4>

          <DatePicker
            name="date"
            selected={booking.date}
            onChange={handleDate}
            isClearable
            dateFormat="yyyy/MM/dd"
            placeholderText="No Date Specified"
          />

          <h4>Time</h4>
          <select name="time" onChange={handleChange}>
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option value="1330">1330-1430</option>
          </select>

          <div>
            <input type="submit" value="Submit" />
          </div>
        </form>
      </div>
    );
  
}

export default BookingPage;

