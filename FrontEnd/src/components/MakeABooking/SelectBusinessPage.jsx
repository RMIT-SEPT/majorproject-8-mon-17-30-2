import React, {useState, useEffect} from "react";
import BusinessCard from "./BusinessCard";
import "../../css/SelectABusiness.css";
import BusinessService from "../../services/BusinessService";

function SelectBusinessPage(){

    const [businesses, setBussiness] = useState([]);
    useEffect(() => {

        BusinessService.getAllBusinesses()
        .then((response) =>{
            console.log(response.data);
        })
        .catch(() => {

        })


    }, []);

    return (
        <div className="booking-summary-heading">
        <h1 className="select-business-page-header">Select a Business</h1>
        <ul className="list-group business-card-list-group">
            {businesses.length > 0 ? <BusinessCard businessName="John's Barber"/> : 
            <div>
                <h1> No businesses available </h1>
            </div>
            }
           
        </ul>
    </div>
    )
}

export default SelectBusinessPage;