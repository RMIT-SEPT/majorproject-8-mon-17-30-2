import React, { useState, useEffect, Component } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AuthenticationService from "../../services/AuthenticationService";
import PostRequestService from "../../services/PostRequestService";
import moment from "moment";
import CustomerService from "../../services/CustomerService";
import WorkerService from "../../services/WorkerService";

class BookingPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      service: "",
      worker: "",
      date: "",
      time: "",
    };

    this.handleService = this.handleService.bind(this);
    this.handleWorker = this.handleWorker.bind(this);
    this.handleDate = this.handleDate.bind(this);
    this.handleTime = this.handleTime.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleService(event) {
    this.setState({ service: event.target.value });
  }
  handleWorker(event) {
    this.setState({ worker: event.target.value });
  }
  handleDate(event) {
    this.setState({ date: event.target.value });
  }
  handleTime(event) {
    this.setState({ time: event.target.value });
  }
  handleChange = (date) => {
    this.setState({
      date: date,
    });
  };

  handleSubmit(event) {

    event.preventDefault();
    let convertedDate = moment(this.date).format("YYYY-MM-DD");

    const newBooking = {
      customer: CustomerService.getCustomerById(AuthenticationService.getLoggedInId()),
      worker: WorkerService.getWorkerById(this.state.worker),
      service: {
        title: this.state.service,
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
          alert("Error");
        }
      })
      .catch((error => {
        console.error(error.message);
      }));
  }

  render() {
    return (
      <div className="form">
        <form onSubmit={this.handleSubmit}>
          <h4>Service</h4>
          <select required="true"
            name="1"
            value={this.state.service}
            onChange={this.handleService}
          >
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option value="Haircut">Haircut</option>
            <option value="BeardTrim">BeardTrim</option>
          </select>

          <h4>Worker</h4>
          <select
            name="2"
            value={this.state.worker}
            onChange={this.handleWorker}
          >
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option value="1">John</option>
          </select>

          <h4>Date</h4>

          <DatePicker
            selected={this.state.date}
            onChange={this.handleChange}
            isClearable
            dateFormat="yyyy/MM/dd"
            placeholderText="No Date Specified"
          />

          <h4>Time</h4>
          <select value={this.state.time} onChange={this.handleTime}>
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
}

export default BookingPage;

