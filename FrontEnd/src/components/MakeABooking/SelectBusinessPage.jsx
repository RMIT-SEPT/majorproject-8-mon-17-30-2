import React, {useState, useEffect} from "react";
import BusinessCard from "./BusinessCard";
import "../../css/SelectABusiness.css";
import BusinessService from "../../services/BusinessService";

/*
    For select a business page,
    Shows a list of businesses using the backend api /api/business
    Each business has a dynamic routing to /bookings/create/:businessId
*/
function SelectBusinessPage(){

    const [businesses, setBusiness] = useState([]);
    useEffect(() => {
        
        BusinessService.getAllBusinesses()
        .then((response) =>{
          
            setBusiness(response.data);
        })
        .catch(() => {
            console.log("Error in getting businesses");
            setBusiness([]);
        })


    }, []);

    return (
        <div className="booking-summary-heading">
        <h1 className="select-business-page-header">Select a business</h1>
        <ul className="list-group business-card-list-group">
            
            {businesses.length > 0 ? businesses.map(business => <BusinessCard key={business.id} id={business.id} businessName={business.name}/>) : 
            <div>
                <h1> No businesses available </h1>
            </div>
            }
           
        </ul>
    </div>
    )
}

export default SelectBusinessPage;