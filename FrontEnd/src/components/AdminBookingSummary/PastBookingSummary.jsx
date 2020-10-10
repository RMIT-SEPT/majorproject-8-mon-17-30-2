import React, {useState, useEffect} from "react";
import BusinessService from "../../services/BusinessService";
import AdminService from "../../services/AdminService";
import AuthenticationService from "../../services/AuthenticationService";
import BootstrapTable from 'react-bootstrap-table-next';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';

function PastBookingSummary(props){

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

    useEffect(() => {
        AdminService.getAdminById(AuthenticationService.getLoggedInId()).then(response => {
            let businessId = response.data.businessId;
            getPastBookings(businessId);
        }).catch();
    }, []);

    function getPastBookings(businessId){
        BusinessService.getBusinessPastBookings(businessId).then(response => {
            setBookings(response.data)
        }).catch();
    }

    return(
        
        <div style={style}>
            <header className="bookings-header"><span role="img" aria-label="Time emoji">🕒</span> {props.title} <span role="img" aria-label="Time emoji">🕒</span></header>
            <BootstrapTable sort={{dataField: 'date', order: 'asc'}} keyField='id' data={bookings} columns={columns} />
        </div>
    );
}

export default PastBookingSummary;