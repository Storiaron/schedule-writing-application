import DatePicker from "react-datepicker";
import { eachDayOfInterval } from "date-fns";
import { useState } from "react";

function DailyRequirements() {
  const [date, setDate] = useState(new Date());
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [isSubmitted, setIsSubmitted] = useState(false);
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
    if(minEmployees > preferredEmployees){
      setPreferredEmployees(minEmployees)
    }
  };
  const handlePreferredEmployeeChange = (e, i) => {
    const updatedPreferredEmployees = [...preferredEmployees];
    updatedPreferredEmployees[i] = e.target.value;
    setPreferredEmployees(updatedPreferredEmployees);
  };

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
  const createShifts = () => {
    let shifts = []
    for(let i = 0; i < numOfShifts; i++){
      let shift = {};
      shift.shiftStart = shiftStart[i];
      shift.shiftEnd = shiftEnd[i];
      shift.minEmployees = minEmployees[i];
      shift.preferredEmployees = preferredEmployees[i];
      shifts.push(shift);
    }
    return shifts;
  }

  const handleInterval = () => {
    const dates = eachDayOfInterval({ start: startDate, end: endDate });
    return dates.map((date, index) => ({
      date: date,
      shifts: createShifts(),
    }));
  };
  const handleSubmit = () => {
    let requestData;
    if (startDate !== endDate) {
      requestData = handleInterval();
    } else {
      requestData = {
        date: startDate,
        shifts: createShifts(),
      };
    }
    fetch("/api/schedule", {
      method: "POST",
      headers: {
        'Authorization' : localStorage.getItem("token"),
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    });
    setIsSubmitted(true);
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
        <label>Min employees:</label>
        <input
          type="number"
          min={1}
          placeholder={1}
          onChange={(e) => handleMinEmployeeChange(e, i)}
        />
        <label>Preferred employees:</label>
        <input
          type="number"
          min={minEmployees}
          placeholder={1}
          onChange={(e) => handlePreferredEmployeeChange(e, i)}
        />
      </div>
    );
  }

  return isSubmitted ? (
    <div>
    <div>Succesfully Submitted! Anything else?</div>
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
    </div>
  ) : <div>
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
</div>;
}

export default DailyRequirements;
