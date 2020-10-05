import React, {useEffect, useState} from "react";
import TimeRange from 'react-time-range';
import moment from 'moment';
import { Button } from 'react-bootstrap'

// props: workSlot, availableServices, onSubmit 
function EditWorkSlot(props){
  
    const [startTime, setStartTime] = useState(moment('2000-01-01 ' + props.workSlot.startTime, moment.ISO_8601).toString());
    const [endTime, setEndTime] = useState(moment('2000-01-01' + props.workSlot.endTime, moment.ISO_8601).toString());

    useEffect(() =>{
        if(props.workSlot){
            setStartTime(moment('2000-01-01 ' + props.workSlot.startTime, moment.ISO_8601).toString());
            setEndTime(moment('2000-01-01 ' + props.workSlot.endTime, moment.ISO_8601).toString());
        }
    },[props.workSlot]);

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
            <h5>New time:</h5>
            <TimeRange
                startMoment={startTime}
                endMoment={endTime}
                onStartTimeChange={handleStart}
                onEndTimeChange={handleEnd}
                minuteIncrement='15'
            />

            <div className="footer">
                <Button type="submit">
                Save
                </Button>
            </div>
            </form>
        </div>
    );
}
export default EditWorkSlot;