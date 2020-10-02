import React, {useEffect, useState } from "react";
import TimeRange from 'react-time-range';
import moment from 'moment';
import "../../css/AddSlots.css";
import { Button, Form } from 'react-bootstrap';
import Select from 'react-select'

// props: bookingSlot, availableservices, {onSubmit} 
function EditBookingSlot(props){

    const [startTime, setStartTime] = useState();
    const [endTime, setEndTime] = useState();
    const [availableServices, setAvailableServices] = useState([]);
    const [options, setOptions] = useState([]); 
    const [workSlotId, setWorkSlotId] = useState([]); 

    // const [startTime, setStartTime] = useState(moment('2000-01-01 ', moment.ISO_8601).toString());
    // const [endTime, setEndTime] = useState(moment('2000-01-01 ', moment.ISO_8601).toString());   
    
    useEffect(() =>{
        if(props.bookingSlot){
            setStartTime(moment('2000-01-01 ' + props.bookingSlot.startTime, moment.ISO_8601).toString());
            setEndTime(moment('2000-01-01 ' + props.bookingSlot.endTime, moment.ISO_8601).toString());
        }
        setAvailableServices(props.availableServices);
        setOptions(props.availableServices.map((d) => {
            return{
                select: serviceIsChosen(d.id) ? true : false, 
                id: d.id,
                title: d.title
            };
        }));
    },[props.workSlot, props.bookingSlot]);

    // boolean check to see if a service is already available in the bookingslot, to pre-tick the box when editing
    function serviceIsChosen(serviceId){
        return (props.bookingSlot.availableServices.filter(e => e.id === serviceId).length) > 0;
    }

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
                <h5>New time:</h5>
                <TimeRange
                    startMoment={startTime}
                    endMoment={endTime}
                    onStartTimeChange={handleStart}
                    onEndTimeChange={handleEnd}
                    minuteIncrement='15'
                />
                <h5>New services:</h5>
                <table className="services">
                    {serviceOptions} 
                </table>
                <div className="footer">
                    <Button type="submit">
                    Save
                    </Button>
                </div>
            </form>
        </div>
    );
}
export default EditBookingSlot;