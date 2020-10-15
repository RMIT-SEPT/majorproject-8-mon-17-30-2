import React, {useState} from "react";
import TimeRange from 'react-time-range';
import moment from 'moment';
import { Button } from 'react-bootstrap'

// props: date (string), {onSubmit} 
function AddWorkSlot(props){
  
    const [startTime, setStartTime] = useState(moment('2000-01-01 09:00:00', moment.ISO_8601).toString());
    const [endTime, setEndTime] = useState(moment('2000-01-01 17:00:00', moment.ISO_8601).toString());

    function handleStart(newStart){
        setStartTime(newStart.startTime)
    }

    function handleEnd(newEnd){
        setEndTime(newEnd.endTime)
    }

    function passChoices(event){
        event.preventDefault();
        const startString = moment(startTime).format('HH:mm');
        const endString = moment(endTime).format('HH:mm');
        props.onSubmit(startString, endString);
    }

    return (
        <div style={{textAlign: "center"}}>  
            <form onSubmit={passChoices}>
            <h4>Create a Workslot on {props.date}</h4>

            <TimeRange
                startMoment={startTime}
                endMoment={endTime}
                onStartTimeChange={handleStart}
                onEndTimeChange={handleEnd}
                minuteIncrement='15'
            />
      
            <div className="footer">
                <Button type="submit">
                Add
                </Button>
            </div>
            </form>
        </div>
    );
}
export default AddWorkSlot;