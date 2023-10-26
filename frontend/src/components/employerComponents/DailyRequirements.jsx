import DatePicker from "react-datepicker";
import { eachDayOfInterval } from 'date-fns';
import { useState } from "react";

function DailyRequirements() {
  const [date, setDate] = useState(new Date());
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [shiftStart, setShiftStart] = useState([]);
  const [shiftEnd, setShiftEnd] = useState([]);
  const [minEmployees, setMinEmployees] = useState([]);
  const [preferredEmployees, setPreferredEmployees] = useState([]);
  const [numOfShifts, setNumOfShifts] = useState(1);

  const handleChange = (range) => {
    const [startDate, endDate] = range;
    setStartDate(startDate);
    setEndDate(endDate);
  };

  const handleMinEmployeeChange = (e, i) => {
    const updatedMinEmployees = [...minEmployees];
    updatedMinEmployees[i] = e.target.value;
    setMinEmployees(updatedMinEmployees);
  }
  const handlePreferredEmployeeChange = (e, i) => {
    const updatedPreferredEmployees = [...preferredEmployees];
    updatedPreferredEmployees[i] = e.target.value;
    setMinEmployees(updatedPreferredEmployees);
  }

  const handleShiftStartChange = (e, i) => {
    const updatedShiftStart = [...shiftStart];
    updatedShiftStart[i] = e.target.value;
    setShiftStart(updatedShiftStart);
  };

  const handleShiftEndChange = (e, i) => {
    const updatedShiftEnd = [...shiftEnd];
    updatedShiftEnd[i] = e.target.value;
    setShiftEnd(updatedShiftEnd);
  };

  const handleInterval = () => {
    const dates = eachDayOfInterval({ start: startDate, end: endDate });
    return dates.map((date, index) => ({
      "date": date,
      "shifts": [
        {
          "shiftStart": shiftStart[index] || "00:01",
          "shiftEnd": shiftEnd[index] || "00:01",
          "minEmployees": minEmployees[index] || 1,
          "preferredEmployees": preferredEmployees[index] || 1,
        },
      ],
    }));
  };

  const handleSubmit = () => {
    let requestData;
    if (startDate !== endDate) {
      requestData = handleInterval();
    } else {
      requestData = {
        "date": startDate,
        "minEmployees": minEmployees[0] || 1,
        "preferredEmployees": preferredEmployees[0] || 1,
      };
    }
    console.log(requestData);
    fetch("/api/schedule", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestData)
    });
  };

  const addShift = () => {
    setNumOfShifts(numOfShifts + 1);
  };

  const shiftSelectors = [];

  for (let i = 0; i < numOfShifts; i++) {
    shiftSelectors.push(
      <div key={i}>
        <label>Shift start</label>
        <input type="time" onChange={(e) => handleShiftStartChange(e, i)} />
        <label>Shift end</label>
        <input type="time" onChange={(e) => handleShiftEndChange(e, i)} />
        <label>Min employees:</label>
        <input type="number" min="1" onChange={(e) => handleMinEmployeeChange(e, i)} />
        <label>Preferred employees:</label>
        <input type="number" onChange={(e) => handlePreferredEmployeeChange(e, i)} />
      </div>
    );
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
      {shiftSelectors}
      <button onClick={addShift}>Add Shift</button>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}

export default DailyRequirements;


