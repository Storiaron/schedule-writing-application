import { Outlet, Link } from "react-router-dom";
function Header(){
    return (
        <>
        <div className="header">
        <Link to="/" className="title">
          Home
        </Link>
        <Link to="/dailyrequirements" className="title">
          Days
        </Link>
        </div>
        <Outlet />
        </>
    )
}

export default Header;