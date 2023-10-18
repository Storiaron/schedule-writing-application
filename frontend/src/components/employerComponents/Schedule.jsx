import { Link } from "react-router-dom";
function Schedule(props) {
    const {employees, schedule, scheduleId} = props;
    const dates = Object.keys(schedule).sort();
    console.log(scheduleId)
    const handleSave = () => {
        fetch("/api/schedule/save", {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
          },
            body: JSON.stringify(scheduleId)  
        })
    }
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Schedule option: </th>
            {dates.map((date) => (
              <th key={date}>{date}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td><Link to={`/api/employee/${employee.name}`}>{employee.name}</Link></td>
              {dates.map((date) => (
                <td key={`${employee.id}+${date}`}>
                  {schedule[date].some((worker) => worker.name === employee.name) ? "Work" : "Off" }
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={handleSave}>Save</button>
    </div>
  );
}

export default Schedule;
