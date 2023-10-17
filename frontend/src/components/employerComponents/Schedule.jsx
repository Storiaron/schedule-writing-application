function Schedule(props) {
  const dates = Object.keys(props.schedule);
  const employees = Object.values(props.schedule);
  console.log(employees)
  const test = Object.entries(props.schedule).sort();
  console.log(test.sort());
  return (
    <div>
      <table>
        <thead>
        <tr>
          {test.map((entry) => (
            <th>{new Date(entry[0]).getDate()}</th>
          ))}
        </tr>
        </thead>
        <tbody>
        <tr>
        {employees.map(employee => <td>{employee.name}</td>)}
        </tr>
        </tbody>
      </table>
    </div>
  );
}

export default Schedule;
