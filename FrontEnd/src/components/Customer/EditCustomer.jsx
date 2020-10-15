import React, { useEffect, useState } from "react";
import "../../css/Register.css";
import CustomerService from "../../services/CustomerService";

/*
    Page that prefills the current logged in customers' details and allows them to edit
*/
function EditCustomer(props){

    //initalise state
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber]  = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [hasInit, setHasInit] = useState(false);
    const [invalidData, setInvalidData] = useState(false);

    useEffect(() =>{
        if(!hasInit){
            init();
        }
    },[name, address, phoneNumber, email, username, password, invalidData, hasInit]);

    function init(){
        CustomerService.getCustomerById(props.customerId)
        .then((response) =>{
            setName(response.data.name);
            setAddress(response.data.address);
            setPhoneNumber(response.data.phoneNumber);
            setEmail(response.data.email);
            setUsername(response.data.username);
            setPassword(response.data.password);
        });
        setHasInit(true);
    }
    //send new data to backend to edit customer
    function handleSubmit(event){
        event.preventDefault();
        const customer = {
            name: name,
            address: address,
            phoneNumber: phoneNumber,
            email: email,
            username: username,
            password: password
        }
        CustomerService.updateCustomer(props.customerId, customer)
        .then((response) =>{
            if(response.data != null){
                alert("Details saved!");
            }
        }).catch(() => {
            setInvalidData(true);
        });
    }

    return (
        <div className="Register" method="POST">  
        <form onSubmit={handleSubmit}>
            <header className="Register-header">Edit account details</header>

            <div className="form">
            {invalidData && <div className="alert alert-danger"> Invalid Credentials </div>}
            <div className="form-input">
                <label>Full Name:</label>
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
                    placeholder="username"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                    required
                />
            </div>
            <div className="form-input">
                <label>Password:</label>
                <input
                    type="password"
                    name="password"
                    placeholder="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    required
                />
            </div>
            </div>
            <div className="footer">
            <button className="button buttonshadow" type="submit">
                Save
            </button>
            </div>
        </form>
        </div>
    );
   

}

export default EditCustomer;