import 'bootstrap/dist/css/bootstrap.min.css';
import baseURL from '../../constants/baseUrls';
import {useEffect, useState } from "react";
import { useNavigate, Link, replace } from 'react-router-dom';

const LoginForm = () => {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const signInHandler = async (e) => {
        e.preventDefault();
        console.log("Signing in with ", userName, password);
        try{
            const loginResponse = await fetch("http://localhost:8080/login", {
                method : "POST",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    username : userName,
                    password : password
                })
            });
            if(loginResponse.ok){
                const loginData = await loginResponse.json();
                console.log("Login successful");
                localStorage.setItem("jwt", loginData.data.jwt);
                localStorage.setItem("currentUserID", loginData.data.userID);
                navigate("/home",  { replace: true });
            }
            else{
                alert("Login failed. Please check your credentials.");
            }
        }
        catch(err){
            console.log("Error during login: ", err);
            alert("Login failed. Please try again.");
        }
    };
    return (
        <div className='d-flex justify-content-center align-items-center vh-100 bg-light'>
           <form onSubmit={signInHandler}>
                <h1 className="h3 mb-3 fw-normal">Please sign in</h1>

                <div className="form-floating mt-3 mb-3">
                    <input
                    type="text"
                    className="form-control"
                    id="floatingInput"
                    placeholder="enter username"
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                    />
                    <label htmlFor="floatingInput">Username</label>
                </div>

                <div className="form-floating  mb-3">
                    <input
                    type="password"
                    className="form-control"
                    id="floatingPassword"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    />
                    <label htmlFor="floatingPassword">Password</label>
                </div>  
                <button className="btn btn-primary w-100 py-2" type="submit" >
                    Sign in
                </button>
                <p className='mt-2'>
                    <Link to = "/register">Create a new account</Link>
                </p>
            </form>
        </div>
    );
};

export default LoginForm;