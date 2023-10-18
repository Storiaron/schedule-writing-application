import {useState, useEffect} from "react";
import DatePicker from "react-datepicker";
import Schedule from "./Schedule";
function ScheduleRequest(){
    const [selectedOption, setSelectedOption] = useState('');
    const [date, setDate] = useState(new Date());
    const [options, setOptions] = useState(null);
    const [schedule, setSchedule] = useState(null);
    const [scheduleId, setScheduleId] = useState(null);
    const [employees, setEmployees] = useState('');

    const fetchScheduleOptions = async () => {
        const response = await fetch('/api/schedule/options')
        if(response.ok){
            const optionsData = await response.json();
            setOptions(optionsData);
        } 
    }
    const fetchEmployees = async () => {
        const response = await fetch('/api/employee')
        if(response.ok){
            const employeeData = await response.json();
            setEmployees(employeeData);
        } 
    }
    useEffect(() => {
        fetchScheduleOptions();
        fetchEmployees();
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
            setScheduleId(schedule.id);
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
          {schedule ? <Schedule schedule={schedule} scheduleId={scheduleId} employees={employees}/> : <></>}
        </div>
      ) : <div>Loading, please wait...</div>;
}

export default ScheduleRequest;