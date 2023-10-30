import { useState } from 'react';
import { Link } from 'react-router-dom';

const DropdownMenu = () => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);


  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };

  return (
    <div className="dropdown">
      <button onClick={toggleDropdown}>Menu</button>
      {isDropdownOpen && (
        <ul className="dropdown-content">
          <li onClick={toggleDropdown}><Link to="/schedule" >generate schedule</Link></li>
          <li onClick={toggleDropdown}><Link to="/dailyrequirements">set daily requirements</Link></li>
          <li onClick={toggleDropdown}><Link to="/request">send day-off requests</Link></li>
        </ul>
      )}
    </div>
  );
};

export default DropdownMenu;
