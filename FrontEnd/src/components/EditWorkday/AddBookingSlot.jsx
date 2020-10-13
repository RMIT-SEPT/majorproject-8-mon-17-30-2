import React, {useEffect, useState } from "react";
import TimeRange from 'react-time-range';
import moment from 'moment';
import "../../css/AddSlots.css";
import { Button} from 'react-bootstrap';

// props: workSlot, services, {onSubmit} 
function AddBookingSlot(props){

    const [startTime, setStartTime] = useState();
    const [endTime, setEndTime] = useState();
    // const [availableServices, setAvailableServices] = useState([]);

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
        // setAvailableServices(props.availableServices);
        setOptions(props.availableServices.map((d) => {
            return{
                select: false, 
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
        props.onSubmit(startString, endString, workSlotId, chosenServiceIds);
    }

    let serviceOptions = (
        options.map((d, i) =>
        <tr>
            <td>
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
                />
            </td>
            <td>
                {d.title}
            </td>                
        </tr>
        )
    )

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
                <table className="services">
                    {serviceOptions} 
                </table>
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