import React, { useEffect, useState } from "react";
import "../../css/Register.css";
import WorkerService from "../../services/WorkerService";
import AuthenticationService from "../../services/AuthenticationService"
import { useHistory } from "react-router-dom";

function AddWorker(){

    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber]  = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [business, setBusiness] = useState("");
    // const [hasInit, setHasInit] = useState(false);
    const [invalidData, setInvalidData] = useState(false);
    let history = useHistory()

    function handleSubmit(event){
        event.preventDefault();
        const worker = {
            name: name,
            address: address,
            phoneNumber: phoneNumber,
            email: email,
            username: username,
            password: password,
            businessId: AuthenticationService.getBusinessId()
        }
        console.log("AAAAA", worker);
        WorkerService.addWorker(worker)
        .then((response) =>{
            if(response.data != null){
                alert("Worker Added!");
                history.push("/workers");
            }
        }).catch(() => {
            setInvalidData(true);
        });
    }

    return (
        <div className="Register" method="POST">  
        <form onSubmit={handleSubmit}>
            <header className="Register-header">Add New Employee</header>

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
                    placeholder="mission123"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    form="parentForm"
                    required
                />
            </div>
            </div>
            <div className="footer">
            <button className="button buttonshadow" type="submit">
                Add
            </button>
            </div>
        </form>
        </div>
    );
   

}

export default AddWorker;