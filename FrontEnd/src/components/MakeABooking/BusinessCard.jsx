import React from "react";

function BusinessCard(props){
    return (
       
        <div className="card w-25 list-group-item text-center business-card">
            <div className="card-body">
                <h5 className="card-text">{props.businessName}</h5>
            </div>
        </div>
    );
}

export default BusinessCard;