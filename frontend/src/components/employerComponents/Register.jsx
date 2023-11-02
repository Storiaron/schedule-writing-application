import { useState } from "react";
function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("Employee");
  const [hoursPerMonth, setHoursPerMonth] = useState(0);
    const handleEnter = (event) => {
      if (event.key === "enter") {
        handleSubmit();
      }
    };
    const handleUsernameChange = (event) => {
      setUsername(event.target.value);
    };
    const handlePasswordChange = (event) => {
      setPassword(event.target.value);
    };
    const handleWorkHourChange = (event) => {
      setHoursPerMonth(event.target.value);
    };

    const handleSubmit = async (event) => {
      event.preventDefault();
      fetch("/api/employee", {
        method: "POST",
        headers: {
          "Authorization": localStorage.getItem("token"),
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
          role: role,
          hoursPerMonth: hoursPerMonth,
        }),
      });
    };
    return (
      <div className="signupform">
        <h2>Add Employee</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <div className="label">
              <label htmlFor="username">Username</label>
            </div>
            <input
              type="text"
              id="username"
              onChange={handleUsernameChange}
              onKeyDown={handleEnter}
            />
          </div>
          <div className="label">
            <label htmlFor="password">Password</label>
          </div>
          <div>
          <input
            type="password"
            id="password"
            onChange={handlePasswordChange}
            onKeyDown={handleEnter}
          />
          </div>
          <div className="position">Position</div>
          <div>
          <select onSelect={(e) => setRole(e.target.value)}>
            <option>Employee</option>
            <option>Admin</option>
          </select>
          </div>
          <div>
          <label>Hours worked per month</label>
          </div>
          <input type="number" onChange={(e) => handleWorkHourChange(e)} />
          <button type="submit">Add</button>
        </form>
      </div>
    );
  };
export default Register;
