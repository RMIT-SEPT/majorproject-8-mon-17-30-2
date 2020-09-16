import React from "react";
import AddBoxIcon from '@material-ui/icons/AddBox';
import {Link} from "react-router-dom";



function AddEmployeeButton(props){


return (<div>
            <Link to="/" className="employee-link">
                <button type="button" className="btn btn-primary list-group-item add-task-button" >
                    <li>
                        Add Employee...
                        <AddBoxIcon  className="icon-margin"/>
                    </li>
                </button>
            </Link>
        </div>);
}



export default AddEmployeeButton;