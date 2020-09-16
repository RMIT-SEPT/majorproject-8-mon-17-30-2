import React from "react";
import EmployeeList from "./EmployeeList";
import "../../css/ManageEmployees.css"
function ManageEmployeesPage() {
    return(
        <div>
            <div className="container-fluid">
            <h1 className="display-4 list-title">Employees</h1>
                <EmployeeList />
            </div>
        
      </div>
    );

}
export default ManageEmployeesPage;