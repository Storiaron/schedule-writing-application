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