import React from "react";
import WorkdaySchedule from "./WorkdaySchedule";
import moment from 'moment';
import "../../css/WeekSchedule.css"

function WeekSchedule(props){

    const day0 = moment().format("yyyy-MM-DD").toString();
    const day1 = moment().add(1, 'd').format("yyyy-MM-DD").toString();
    const day2 = moment().add(2, 'd').format("yyyy-MM-DD").toString();
    const day3 = moment().add(3, 'd').format("yyyy-MM-DD").toString();
    const day4 = moment().add(4, 'd').format("yyyy-MM-DD").toString();
    const day5 = moment().add(5, 'd').format("yyyy-MM-DD").toString();
    const day6 = moment().add(6, 'd').format("yyyy-MM-DD").toString();

    return( 
        <>
        <h1 className="display-4 text-center"><br/>7-day schedule from {day0}</h1><br/><br/>
        <div className="week">            
            <table style={{border: "none"}}>
                <tr>
                    <th><h4>{moment().format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(1, 'd').format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(2, 'd').format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(3, 'd').format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(4, 'd').format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(5, 'd').format("dddd Do MMM")}</h4></th>
                    <th><h4>{moment().add(6, 'd').format("dddd Do MMM")}</h4></th>
                </tr>
                <tr>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day0} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day1} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day2} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day3} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day4} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day5} noWeek noHead/>
                        </div>
                    </td>
                    <td className="day">
                        <div className="day">
                            <WorkdaySchedule workerId={props.match.params.workerId} date={day6} noWeek noHead/>
                        </div>
                    </td>
                </tr>
            </table>  
        </div>
        </>
    );
}
export default WeekSchedule;