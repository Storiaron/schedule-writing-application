import { Link } from "react-router-dom";

function Schedule(props) {
  const {employees, schedule, scheduleId} = props;
  const handleSave = () => {
    fetch("/api/schedule/save", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({id: scheduleId, schedule: schedule}),
    });
  }

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Schedule option:</th>
            {schedule.map((entry) => (
              <th key={entry.day.id}>{new Date(entry.day.date).getDate()}</th>
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
                <td key={`${employee.id}+${entry.day.date}`}>
                  {entry.scheduledEmployees.some(
                    (worker) => worker.name === employee.name
                  )
                    ? "Work"
                    : "Off"}
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

