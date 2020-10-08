import React, { useEffect, useState } from "react";
import View from "react";
import "../../css/Register.css";
import CustomerService from "../../services/CustomerService";
import {Button} from "react-bootstrap";


function AdminBusinessSignup(props){

    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber]  = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [businessName, setBusinessName] = useState("");
    const [services, setServices] = useState([]);
    const [newServiceTitle, setNewServiceTitle] = useState("");
    const [newServiceDesc, setNewServiceDesc] = useState("");
    const [invalidData, setInvalidData] = useState(false);

    useEffect(() => {

    },[services])

    function handleNewService(event){
        event.preventDefault();
        console.log("wtf");
        console.log(services);
        const service = {
            title: newServiceTitle,
            description: newServiceDesc
        }
        setNewServiceDesc("");
        setNewServiceTitle("");
        setServices(services.concat(service));
    }    
    
    function handleSubmit(event){
        event.preventDefault();
        const admin = {
            name: name,
            address: address,
            phoneNumber: phoneNumber,
            email: email,
            username: username,
            password: password
        }
        const business = {
            name: businessName,
            services: services
        }
        // BusinessService.signupBusinessAdmin(business, admin)
        // .then((response) =>{
        //     if(response.data != null){
        //         alert("Details saved!");
        //     }
        // }).catch(() => {
        //     setInvalidData(true);
        // });
    }

    return (
        <div className="Register" method="POST">  
            <form id="parentForm" onSubmit={handleSubmit}></form>
            <form id="serviceForm" onSubmit={handleNewService}></form>

            <header className="display-4">Register a Business</header>
            <header className="display-5">And create an Admin account</header>

            <div className="form">
            {invalidData && <div className="alert alert-danger"> Invalid Credentials </div>}
            <div className="form-input">
                <label>Admin Name:</label>
                <input
                    type="fullname"
                    name="fullname"
                    placeholder="John Doe"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Address:</label>
                <input
                    type="text"
                    name="address"
                    placeholder="123 Apple Street"
                    value={address}
                    onChange={e => setAddress(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Phone Number:</label>
                <input
                    type="text"
                    name="mobile"
                    placeholder="12345678"
                    value={phoneNumber}
                    onChange={e => setPhoneNumber(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Email:</label>
                <input
                    type="text"
                    name="email"
                    placeholder="john.doe@email.com"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Username:</label>
                <input
                    type="text"
                    name="username"
                    placeholder="user123"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Password:</label>
                <input
                    type="text"
                    name="password"
                    placeholder="hunter2"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    required
                />
            </div>
            <hr
                style={{
                    border: "1px solid black",
                    width: "400px"
                }}>            
            </hr>
            <div className="form-input">
                <label>Business Name:</label>
                <input
                    type="text"
                    name="businessName"
                    placeholder="Jack's Gym"
                    value={password}
                    onChange={e => setBusinessName(e.target.value)}
                    required
                />
            </div>
                       
            <div className="text-left">
                <br/><label>Services:</label><br/>
                {services.length > 0 ? services.map(service => {
                    return (
                        <p>{service.title} : {service.description}</p>
                    )
                }) : <p className="text-muted">Please add services that your business provides.</p>}

                
                <div style={{border: "1px solid black"}}>
                    <div>
                        <p>Service Title:</p>
                        <input
                            type="text"
                            name="serviceTitle"
                            placeholder="Spin Class"
                            value={newServiceTitle}
                            onChange={e => setNewServiceTitle(e.target.value)}
                            form="serviceForm"
                            required
                        />
                        <br/>
                        <p>Service Description:</p>
                        <input
                            type="text"
                            name="serviceDesc"
                            placeholder="A class where you ride bikes on the spot."
                            value={newServiceDesc}
                            onChange={e => setNewServiceDesc(e.target.value)}
                            form="serviceForm"
                            required
                        /><br/>
                        <button type="submit" form="serviceForm">Add Service</button>
                    </div>
                </div>

            </div>

            </div>
            <div className="footer">
            <button className="button buttonshadow" type="submit">
                Save
            </button>
            </div>

        </div>
    );
   

}

export default AdminBusinessSignup;