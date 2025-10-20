
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();
  const [currentNavItem, setCurrentNavItem] = useState('');
  const navItems  = [
    {id : 'home', label : 'Home', link : '/home'},
    {id : 'newBlog', label : 'New Blog', link : '/newBlog'},
    {id : 'myBlogs', label : 'My Blogs', link : '/myBlogs'},
    {id : 'about', label : 'About', link : '/profile/'+localStorage.getItem("currentUserID")},
  ];
  return (
    <header className="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
      <a
        href="/"
        className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"
      >
        <svg className="bi me-2" width="40" height="32" aria-hidden="true">
          <use xlinkHref="#bootstrap"></use>
        </svg>
        <span className="fs-4">Simple Blog</span>
      </a>

      <ul className="nav nav-pills">
        {navItems.map((item) => (
          <li className="nav-item"  key={item.id}>
            <Link to={item.link} className={`nav-link ${currentNavItem === item.id ? "active" : ""}`} onClick={()=>{setCurrentNavItem(item.id)}}>{item.label}</Link>
          </li>
        ))}
      </ul>
      <button className={`btn ${localStorage.getItem("jwt") ? "btn-danger" : "btn-success"}  btn-sm mx-2`} onClick = {() => {
                if(localStorage.getItem("jwt")){
                  localStorage.removeItem("jwt");
                  navigate("/login");
                  setCurrentNavItem('');
                }
                else{
                  navigate("/login"); 
                  setCurrentNavItem('');
                }
              }}>
                  {localStorage.getItem("jwt") ? "Logout" : "Login"}
      </button>
      
    </header>
  );
};

export default Header;
