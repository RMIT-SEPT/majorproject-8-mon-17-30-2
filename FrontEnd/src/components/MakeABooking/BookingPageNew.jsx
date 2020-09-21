import React, { useState, useEffect, Component } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AuthenticationService from "../../services/AuthenticationService";
import PostRequestService from "../../services/PostRequestService";
import moment from "moment";
import CustomerService from "../../services/CustomerService";
import WorkerService from "../../services/WorkerService";
import BusinessService from "../../services/BusinessService";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

class BookingPageNew extends Component {

  constructor(props) {
    super(props);

    this.state = {
      business: "",
      services: [],
      service: "",
      workers: [],
      worker: "",
      date: "",
      customer: ""
    };

    this.handleService = this.handleService.bind(this);
    this.handleWorker = this.handleWorker.bind(this);
    this.handleDate = this.handleDate.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);

  }

  componentDidMount() {
    CustomerService.getCustomerById(AuthenticationService.getLoggedInId())
    .then((response) =>{
      this.setState({
        customer: response.data
      })
  });
    BusinessService.getBusinessById(this.props.match.params.businessId)
    .then((response) =>{
      this.setState({
        business: response.data,
        services: response.data.services,
        workers: response.data.workers
      })
    })
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
  handleChange = (date) => {
    this.setState({
      date: date,
    });
  };

  render() {
    let services = <option value="" disabled hidden></option>
    if(this.state.services.length > 0){
      services = this.state.services.map((service) => {
        return <option key={service.id} value={service.id}>{service.title}</option>
      });
    };
    
    let workers = <option value="" disabled hidden></option>
    if(this.state.workers.length > 0){
      workers = this.state.workers.map((worker) => {
        return <option key={worker.id} value={worker.id}>{worker.name}</option>
      });
    };
    
    return (
      <>     
      <div className="form">
        <h1>Create a booking at {this.state.business.name}:</h1>
        <br/>
        <form onSubmit={this.handleShow}>

          <h4>Service</h4>
          <select required="true"
            name="1"
            value={this.state.service}
            onChange={this.handleService}
          >
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option key="" value="">-Any-</option>
            {services}
          </select>

          <br/><br/>
          <h4>Worker</h4>
          <select
            name="2"
            value={this.state.worker}
            onChange={this.handleWorker}>
            <option value="" disabled selected hidden>
              Select an option
            </option>
            <option key="" value="">-Any-</option>
            {workers}
          </select>

          <br/><br/>
          <h4>Date</h4>
          <DatePicker
            selected={this.state.date}
            onChange={this.handleChange}
            isClearable
            dateFormat="yyyy/MM/dd"
            placeholderText="No Date Specified"
          />
          <br/><br/>

          <div>
            <input type="submit" value="Search for Bookings" />
          </div>
        </form>
      </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      </>
    );
  }
}

export default BookingPageNew;