import {useState, useEffect} from "react";
import DatePicker from "react-datepicker";
import Schedule from "./Schedule";
function ScheduleRequest(){
    const [selectedOption, setSelectedOption] = useState('');
    const [date, setDate] = useState(new Date());
    const [options, setOptions] = useState(null);
    const [schedule, setSchedule] = useState(null);

    const fetchScheduleOptions = async () => {
        const scheduleOptionsData = await fetch('/api/schedule/options')
        if(scheduleOptionsData.ok){
            const optionsData = await scheduleOptionsData.json();
            setOptions(optionsData);
        } 
    }
    useEffect(() => {
        fetchScheduleOptions();
    }, [])

    const handleSubmit = async (e) => {
        e.preventDefault();
        const scheduleData = await fetch('/api/schedule/generate', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
          },
            body: JSON.stringify({startingDate: date, typeofSchedule: selectedOption})  
        })
        if(scheduleData.ok){
            const schedule = await scheduleData.json()
            setSchedule(schedule.schedule);
        }
    }
    return options ? (
        <div>
          <label>Select a schedule option and date:</label>
          <select value={selectedOption} onChange={(e) => setSelectedOption(e.target.value)}>
            <option value="">Select an option</option>
            {options.map((option, index) => (
              <option key={index} value={option}>
                {option}
              </option>
            ))}
          </select>
          <DatePicker selected={date} onChange={date => setDate(date)} />
          <input type="submit" value="Generate schedule" onClick={(e) => handleSubmit(e)}/>
          {schedule ? <Schedule schedule={schedule}/> : <></>}
        </div>
      ) : <div>Loading, please wait...</div>;
}

export default ScheduleRequest;