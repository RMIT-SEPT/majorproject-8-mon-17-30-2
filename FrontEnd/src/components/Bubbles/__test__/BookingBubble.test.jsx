import React from 'react';
import ReactDOM from 'react-dom';
import BookingBubble from '../BookingBubble';
import { render } from '@testing-library/react';

const props = {
    id: "1", 
    serviceTitle: "SERVICE",
    date: "2020-09-01",
    startTime: "1330",
    endTime: "1400",
    workerName: "JOHN",
    test: "test"
}

it("initialises without crashing", ()=>{
    const div = document.createElement("div");
    ReactDOM.render(<BookingBubble booking={props}/>, div);
})

it("renders properly", () =>{
    const {getByTestId} = render(<BookingBubble booking={props}/>)
    expect(getByTestId('bubblecard')).toHaveTextContent("Booking #")
})

it("accepts booking parameters as props", ()=>{
    const {getByTestId} = render(<BookingBubble booking={props}/>);
    expect(getByTestId('bubblecard')).toHaveTextContent("SERVICE");
})

it("accepts and renders a full booking object", ()=>{
    const {getByTestId} = render(<BookingBubble booking={props} />);
    expect(getByTestId('bubblecard')).toHaveTextContent("1");
    expect(getByTestId('bubblecard')).toHaveTextContent("SERVICE");
    expect(getByTestId('bubblecard')).toHaveTextContent("2020-09-01");
    expect(getByTestId('bubblecard')).toHaveTextContent("1330");
    expect(getByTestId('bubblecard')).toHaveTextContent("1400");
    expect(getByTestId('bubblecard')).toHaveTextContent("JOHN");
})