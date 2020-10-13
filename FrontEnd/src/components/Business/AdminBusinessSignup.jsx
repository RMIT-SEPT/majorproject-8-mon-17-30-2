import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "../../css/Register.css";
import BusinessService from "../../services/BusinessService";
import { __RouterContext } from "react-router";


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
    const [newServiceCap, setNewServiceCap] = useState(1);
    const [invalidData, setInvalidData] = useState(false);

    let history = useHistory()

    function handleNewService(event){
        event.preventDefault();
        const service = {
            title: newServiceTitle,
            description: newServiceDesc,
            capacity: newServiceCap
        }
        setNewServiceDesc("");
        setNewServiceTitle("");
        setNewServiceCap(1);
        setServices(services.concat(service));
    }   
    
    function finalise(){
        history.push("/");
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
        console.log(admin);
        console.log(business);
        BusinessService.signUpAdmin(admin)
        .then((response) => {
            if(response.data != null){
                BusinessService.signUpBusiness(response.data.id, business)
                .then((response2) => {
                    if(response2.data != null){
                        alert("Business and Admin registered. Hope you remember your password! Proceeding to login page.");
                        finalise();
                    }
                })
            }
        }).catch(() => {
            setInvalidData(true);
        });
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
                    form="parentForm"
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
                    form="parentForm"
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
                    form="parentForm"
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
                    form="parentForm"
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
                    form="parentForm"
                    required
                />
            </div>
            <div className="form-input">
                <label>Password:</label>
                <input
                    type="password"
                    name="password"
                    placeholder="hunter2"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    form="parentForm"
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
                    placeholder="John's Gym"
                    value={businessName}
                    onChange={e => setBusinessName(e.target.value)}
                    form="parentForm"
                    required
                />
            </div>
                       
            <div className="text-left">
                <br/><label>Services:</label><br/>
                {services.length > 0 ? services.map(service => {
                    return (
                        <p>{service.title} : {service.description} : {service.capacity}</p>
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
                            placeholder="A class where you ride bikes on the spot"
                            value={newServiceDesc}
                            onChange={e => setNewServiceDesc(e.target.value)}
                            form="serviceForm"
                            required
                        /><br/>
                        <p>Service Patron Capacity:</p>
                        <input
                            type="number"
                            name="serviceCap"
                            value={newServiceCap}
                            onChange={e => setNewServiceCap(e.target.value)}
                            form="serviceForm"
                            min="0"
                            required
                        /><br/>
                        <button type="submit" form="serviceForm">Add Service</button>
                    </div>
                </div>

            </div>

            </div>
            <div className="footer">
            <button className="button buttonshadow" type="submit" form="parentForm">
                Register
            </button>
            </div>

        </div>
    );
   

}

export default AdminBusinessSignup;