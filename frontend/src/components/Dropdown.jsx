/* eslint-disable no-unreachable */
/* eslint-disable no-lone-blocks */
import { useState } from 'react';
import { Link } from 'react-router-dom';

const DropdownMenu = () => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);


  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };
  const handleLogOut = () => {
    toggleDropdown();
    localStorage.clear();
  }

  const getDropdownContent = () => {
    const role = localStorage.getItem("role");
    switch(role){
      case "Employer" : {
        return (
          <ul className="dropdown-content">
          <li onClick={toggleDropdown}><Link to="/schedule" >Generate schedule</Link></li>
          <li onClick={toggleDropdown}><Link to="/dailyrequirements">Daily requirements</Link></li>
          <li onClick={toggleDropdown}><Link to="/register">New employee</Link></li>
          <li onClick={toggleDropdown}><Link to="/request">Day-off</Link></li>
          <li onClick={handleLogOut}><Link to="/">Logout</Link></li>
        </ul>
        );
      }
        case "Employee" : {
          return (
          <ul className="dropdown-content">
          <li onClick={toggleDropdown}><Link to="/request">Day-off</Link></li>
          <li onClick={handleLogOut}><Link to="/">Logout</Link></li>
        </ul>
          )
        };
        default : {
          return <ul className="dropdown-content">
          <li onClick={toggleDropdown}><Link to="/">Login</Link></li>
        </ul>
        }
      
    }
  }
  getDropdownContent();
  return (
    <div className="dropdown">
      <button onClick={toggleDropdown}>Menu</button>
      {isDropdownOpen && getDropdownContent()}
    </div>
  );
};

export default DropdownMenu;
