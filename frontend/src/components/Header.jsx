import { Outlet, Link } from "react-router-dom";
function Header(){
    return (
        <>
        <div className="header">
            <Link to="/" className="title">
          Home
        </Link>
        </div>
        <Outlet />
        </>
    )
}

export default Header;