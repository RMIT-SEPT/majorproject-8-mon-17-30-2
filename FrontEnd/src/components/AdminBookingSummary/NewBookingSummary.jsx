import React, { useState, useEffect } from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';

/*
    Page to display ongoing bookings for a businesss utilises table view
*/
function NewBookingSummary(props) {

    const [bookings, setBookings] = useState([]);
    const columns = [{
        dataField: 'id',
        text: 'ID',
        sort: true
    }, {
        dataField: 'businessName',
        text: 'Business',
        sort: true
    }, {
        dataField: 'date',
        text: 'Date',
        sort: "desc"
    }, {
        dataField: 'startTime',
        text: 'Start Time',
        sort: true
    }, {
        dataField: 'endTime',
        text: 'End Time',
        sort: true
    }, {
        dataField: 'serviceTitle',
        text: 'Service',
        sort: true
    }, {
        dataField: 'workerName',
        text: 'Worker',
        sort: true
    }, {
        dataField: 'customerName',
        text: 'Customer',
        sort: true
    }]
    const style = {
        maxWidth: "50%",
        margin: "auto"
    }

    // load bookings based on businessid
    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            let businessId = response.data.businessId;
            getNewBookings(businessId);
        }).catch();
    }, []);
    function getNewBookings(businessId) {
        BusinessService.getBusinessNewBookings(businessId).then(response => {
            setBookings(response.data)
        }).catch();
    }




    return (
        <div>

            <div style={style}>
                <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
                <BootstrapTable sort={{ dataField: 'date', order: 'asc' }} keyField='id' data={bookings} columns={columns} />
            </div>
        </div>
    );
}

export default NewBookingSummary;