import DatePicker from "react-datepicker";
import { eachDayOfInterval } from 'date-fns';
import { useState } from "react";
function DailyRequirements() {
  const [date, setDate] = useState(new Date());
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [minEmployees, setMinEmployees] = useState(1);
  const [preferredEmployees, setPreferredEmployees] = useState(1);
  const handleChange = (range) => {
    const [startDate, endDate] = range;
    setStartDate(startDate);
    setEndDate(endDate);
  };
  const handleRequirementChange = (e, setterFunction) => {
    setterFunction(e.target.value);
  }
  const handleInterval = () => {
    const dates = eachDayOfInterval({start: startDate, end: endDate});
    return dates.map(date => {return {"date": date, "minEmployees": minEmployees, "preferredEmployees": preferredEmployees}});
  }
  const handleSubmit = () => {
    let requestData; 
    if(startDate !== endDate){
        requestData = handleInterval();
    }
    else {
        requestData = { "date" : startDate, "minEmployees" : minEmployees, "preferredEmployees" : preferredEmployees }
    }
    console.log(requestData);
     fetch("/api/schedule" , {
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
      <label>Min employees:</label>
  <input type="number" id="quantity" name="quantity" min="1" onChange={(e) => handleRequirementChange(e, setMinEmployees)}></input>
      <label>Preferred employees:</label>
  <input type="number" id="quantity" name="quantity" min={minEmployees} onChange={(e) => handleRequirementChange(e, setPreferredEmployees)}></input>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}

export default DailyRequirements;
