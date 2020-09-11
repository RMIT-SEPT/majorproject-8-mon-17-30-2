import React from "react";
import {Link} from "react-router-dom";
import {BUSINESS_CARD_LINK} from "../../Utils/utils";

// Single 'BusinessCard' to display each business as a card and provide them with a dynamic route
function BusinessCard(props){
    return (
       
        <Link className="card w-25 list-group-item text-center business-card list-group-item-action" to={`${BUSINESS_CARD_LINK}${props.id}`}>
                <div className="card-body">
                    <h5 className="card-text">{props.businessName}</h5>
                </div>
        </Link>      
    );
}

export default BusinessCard;