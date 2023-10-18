import { Link } from "react-router-dom";
function Schedule(props) {
    const {employees, schedule} = props;
    const dates = Object.keys(schedule).sort();
    console.log("here" , schedule[dates[0]])
    console.log(dates)
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
    </div>
  );
}

export default Schedule;
