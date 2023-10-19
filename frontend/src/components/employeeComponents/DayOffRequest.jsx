import DatePicker from "react-datepicker";
import { eachDayOfInterval } from 'date-fns';
import "react-datepicker/dist/react-datepicker.css";
import { useState } from "react";
function DayOffRequest() {
  //FOR TESTING
  const username = "test03"
  const [date, setDate] = useState(new Date());
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  const handleChange = (range) => {
    const [startDate, endDate] = range;
    setStartDate(startDate);
    setEndDate(endDate);
  };
  const handleInterval = () => {
    const dates = eachDayOfInterval({start: startDate, end: endDate});
    return dates.map(date => {return {"employeeName" : username, "date": date}});
  }
  const handleSubmit = () => {
    let requestData; 
    if(startDate !== endDate){
        requestData = handleInterval();
    }
    else {
        requestData = { "employeeName" : username, "date" : startDate}
    }
     fetch("/api/request" , {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
      },
        body: JSON.stringify(requestData)  
    }) 
  }
  return (
    <div>
      <DatePicker
        placeholderText="select a date"
        selected={date}
        onChange={handleChange}
        startDate={startDate}
        endDate={endDate}
        selectsRange
      />
      <button onClick={handleSubmit}>Submit request</button>
    </div>
  );
}

export default DayOffRequest;
