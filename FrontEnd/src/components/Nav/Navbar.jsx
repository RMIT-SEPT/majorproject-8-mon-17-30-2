import React from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthenticationService from "../../services/AuthenticationService";

//basic navbar with bootstrap
function Navbar(props){
    

   function handleClick(){
      
        AuthenticationService.logout();
        props.setIsLoggedIn(false);
    }


    return (
        <nav className = "navbar navbar-expand-lg navbar-dark bg-dark">
        {props.isLoggedIn ? <Link to = {"/"+ AuthenticationService.getRole().toLowerCase()} className = "navbar-brand nav-text">AGME</Link> : 
        <a className = "navbar-brand nav-text" href= "/">AGME</a>}
            <div className="navbar-collapse" id="navbarNav">
            <ul className = "navbar-nav">
            {props.isLoggedIn ? <Link to = {"/"+ AuthenticationService.getRole().toLowerCase()} className = "nav-link"> <li className = "nav-item">Home</li> </Link> :
                <Link to = "/" className = "nav-link">
                    <li className = "nav-item">Home</li>   
                </Link>
    }

                <Link to = "/about" className = "nav-link">
                    <li className = "nav-item">About & Contact</li>
                </Link>
            </ul>
          
           

            {props.isLoggedIn ? 
                <ul className="navbar-nav ml-auto">
                    <span className = "navbar-text">{AuthenticationService.getLoggedInUserName()}</span>
                    <Link to = "/" className = "nav-link">
                        <li onClick={handleClick} className = "nav-item">Logout</li>
                    </Link>
                    </ul>
                
                : 

                <ul className="navbar-nav ml-auto">
                    <Link to = "/" className = "nav-link">
                        <li className = "nav-item">Login</li>
                    </Link>
                    <Link to = "/register" className = "nav-link">
                        <li className = "nav-item">Register</li>
                    </Link>
        
                    </ul>
                 
            }
                

           
         
            </div>
        </nav>
    );
}

export default Navbar;