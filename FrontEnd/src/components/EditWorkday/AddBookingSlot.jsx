import React, {useEffect, useState } from "react";
import TimeRange from 'react-time-range';
import moment from 'moment';
import "../../css/AddSlots.css";
import { Button, Form } from 'react-bootstrap';
import Select from 'react-select'

// props: workSlot, services, {onSubmit} 
function AddBookingSlot(props){

    const [startTime, setStartTime] = useState();
    const [endTime, setEndTime] = useState();
    const [availableServices, setAvailableServices] = useState([]);
    const [services, setServices] = useState([]);
    const [options, setOptions] = useState([]); 
    const [workSlotId, setWorkSlotId] = useState([]);   

    // const [startTime, setStartTime] = useState(moment('2000-01-01 ', moment.ISO_8601).toString());
    // const [endTime, setEndTime] = useState(moment('2000-01-01 ', moment.ISO_8601).toString());   
    
    useEffect(() =>{
        if(props.workSlot){
            setStartTime(moment('2000-01-01 ' + props.workSlot.startTime, moment.ISO_8601).toString());
            setEndTime(moment('2000-01-01 ' + props.workSlot.endTime, moment.ISO_8601).toString());
            setWorkSlotId(props.workSlot.id);
        }
        setAvailableServices(props.availableServices);
        console.log(props.availableServices);
        setOptions(props.availableServices.map((d) => {
            return{
                select:false, 
                id: d.id,
                title: d.title
            };
        }));
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
        var chosenServices = options.filter(function(o){
            return o.select == true;
        })
        var chosenServiceIds = []
        for(var i = 0; i < chosenServices.length; i++) {
            chosenServiceIds.push(chosenServices[i].id);
        }
        console.log(chosenServices);
        console.log(chosenServiceIds);
        props.onSubmit(startString, endString, workSlotId, chosenServiceIds);
    }

    return (
        <div style={{textAlign: "center"}}>  
            <form onSubmit={passChoices}>
                <h5>At what time is this Booking Slot?</h5>
                <TimeRange
                    startMoment={startTime}
                    endMoment={endTime}
                    onStartTimeChange={handleStart}
                    onEndTimeChange={handleEnd}
                    minuteIncrement='15'
                />
                <h5>Which Services could be offered at this time?</h5>

                {options.map((d, i) =>
                <>
                    <input 
                        className="checkbox"
                        onChange={(thing)=>{
                            let checked=thing.target.checked;
                            setOptions(
                                options.map((data)=>{
                                    if(d.id == data.id){
                                        data.select = checked;
                                    }
                                    return data;
                            }))}} 
                        type="checkbox" 
                        checked={d.select} 
                        value={d.id}
                    />{d.title}
                </>
                )}                

                <div className="footer">
                    <Button type="submit">
                    Add
                    </Button>
                </div>
            </form>
        </div>
    );
}
export default AddBookingSlot;