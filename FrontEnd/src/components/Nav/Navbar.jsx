import React from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

//basic navbar with bootstrap
function Navbar(props){
    return (
        <nav className = "navbar navbar-expand-lg navbar-dark bg-dark">
        <a className = "navbar-brand nav-text" href= "/">AGME</a>
            <div className="navbar-collapse" id="navbarNav">
            <ul className = "navbar-nav">
                <Link to = "/" className = "nav-link">
                    <li className = "nav-item">Home</li>   
                </Link>

                <Link to = "/about" className = "nav-link">
                    <li className = "nav-item">About</li>
                </Link>

                <Link to = "/contact" className = "nav-link">
                    <li className = "nav-item">Contact</li>
                </Link>
            </ul>
          
            <ul className="navbar-nav ml-auto">
                <Link to = "/login" className = "nav-link">
                    <li className = "nav-item">Login</li>
                </Link>
                <Link to = "/register" className = "nav-link">
                    <li className = "nav-item">Register</li>
                </Link>
            </ul>
         
            </div>
        </nav>
    );
}

export default Navbar;