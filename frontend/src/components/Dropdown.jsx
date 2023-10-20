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
          <li><Link to="/schedule">generate schedule</Link></li>
          <li><Link to="/dailyrequirements">set daily requirements</Link></li>
          <li><Link to="/request">send day-off requests</Link></li>
        </ul>
      )}
    </div>
  );
};

export default DropdownMenu;
