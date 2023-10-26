import { Outlet, Link } from "react-router-dom";
import { useState } from "react";
import DropdownMenu from "./Dropdown";
function Header() {
    const [isOpen, isClosed] = useState(false);
  return (
    <>
      <div className="header">
        <Link to="/" className="title">
          Home
        </Link> 
        <DropdownMenu />
      </div>
      <Outlet />
    </>
  );
}

export default Header;
/*
<div className="header-options">
          <Link to="/schedule" className="title">
            Schedule
          </Link>
          <Link to="/dailyrequirements" className="title">
            Daily Requirements
          </Link>
        </div>
        */