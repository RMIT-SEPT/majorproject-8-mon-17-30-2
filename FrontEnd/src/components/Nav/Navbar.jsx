import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

//basic navbar with bootstrap
function Navbar(props){
    return (
        <nav className = "navbar navbar-expand-lg navbar-dark bg-dark">
        <a className = "navbar-brand nav-text" href= "/">AGME</a>
            <div className="navbar-collapse" id="navbarNav">
            <ul className = "navbar-nav">
                <li className = "nav-item"><a className = "nav-link" href ="/">Home</a></li>   
                <li className = "nav-item"><a className = "nav-link" href ="/">About</a></li>
                <li className = "nav-item"><a className = "nav-link" href ="/">Contact</a></li>
            </ul>
          
            <ul class="navbar-nav ml-auto">
                <li className = "nav-item"><a className = "nav-link" href ="/">Login</a></li>
                <li className = "nav-item"><a className = "nav-link" href ="/">Register</a></li>
            </ul>
         
            </div>
        </nav>
    );
}

export default Navbar;