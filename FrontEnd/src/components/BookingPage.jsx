// import React, { Component } from "react";
import React, { useState, useEffect } from "react";
// import React.const [state, setstate] = useState(initialState)
import Select from "react-select";
import Calendar from "react-calendar";
import DatePicker from "react-datepicker";

function Dashboard(props) {
  // Change options to get from backend
  const options = [
    { value: "1", label: "Haircut" },
    { value: "2", label: "Beardtrim" },
    { value: "3", label: "Treatment" },
  ];

  const options2 = [
    { value: "1", label: "John" },
    { value: "2", label: "Tom" },
    { value: "3", label: "Harry" },
  ];

  const [startDate, setStartDate] = useState(null);

  return (
    <div>
      <header className="Register-header">Create a Booking</header>
      <label>Select a Service*</label>
      <Select options={options} />

      <label>Select a Worker(Optional)</label>
      <Select options={options2} />

      <label>Select a Date(Optional)</label>
        <DatePicker
          selected={startDate}
          onChange={(date) => setStartDate(date)}
          isClearable
          placeholderText="No Date Specified"
        />
        <label>Select a Time Slot*</label>
    </div>
  );
}

export default Dashboard;
