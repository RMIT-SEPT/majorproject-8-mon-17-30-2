import React from "react";

function Dashboard(props){

    return(<div className = "container-fluid">
        
            <h1>{props.title}</h1>
            <p>{props.desc}</p>
        
        </div>)



}


export default Dashboard;