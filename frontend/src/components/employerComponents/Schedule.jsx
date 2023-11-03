import { Link } from "react-router-dom";

function Schedule(props) {
  const {employees, schedule, scheduleId} = props;
  const handleSave = () => {
    fetch("/api/schedule/save", {
      method: "PUT",
      headers: {
        'Authorization' : localStorage.getItem("token"),
        "Content-Type": "application/json",
      },
      body: JSON.stringify(scheduleId),
    });
  }

  const getShiftStart = (shiftStart) => {
    let date = new Date(shiftStart);
    let displayedString = "";
    date.getHours() < 10 ? displayedString += "0" + date.getHours() : displayedString += date.getHours();
    displayedString += ":";
    date.getMinutes() < 10 ? displayedString += "0" + date.getMinutes() : displayedString += date.getMinutes();
    return displayedString;
  }

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th></th>
            {schedule.map((entry) => (
              <th key={entry.id}>{new Date(entry.date).getDate()}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>
                <Link to={`/employee/${employee.name}`}>{employee.name}</Link>
              </td>
              {schedule.map((entry) => (
                <td key={`${employee.id}+${entry.date}`}>
                  {entry.shifts.map(shift => shift.scheduledEmployees.some((worker => worker.name === employee.name)) ? getShiftStart(shift.shiftStart) : "Off")}
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

