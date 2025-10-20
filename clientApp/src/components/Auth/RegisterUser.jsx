import { useState } from 'react';
import { useNavigate } from 'react-router';
const RegisterUser = () => {
    const [formUserFirstName, setFormUserFirstName] = useState("");
    const [formUserLastName, setFormUserLastName] = useState("");
    const [formUserMiddleName, setFormUserMiddleName] = useState("");
    const [formUserName, setFormUserName] = useState("");
    const [formUserBio, setFormUserBio] = useState("");
    const [formUserPassword, setFormUserPassword] = useState("");
    const [formUserDareOfBirth, setFormUserDateOfBirth] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const userData = {
            userFirstName: formUserFirstName,
            userLastName: formUserLastName,
            userMiddleName: formUserMiddleName,
            username: formUserName,
            userBio: formUserBio,
            password: formUserPassword,
            dateOfBirth : formUserDareOfBirth
        };
        try{
            const response = await fetch("http://localhost:8080/"+"register", {
                method : "POST",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify(userData)
            });

            if(response.ok){
                alert("Registration successful! Please login.");
                navigate("/login")
            }
            else{
                const responsejson = await response.json();
                alert("Registration failed. Please try again. - Reason: " + responsejson.message);
            }
        }
        catch(err){
            console.log("Error during user registration: ", err);
            alert("Registration failed. Please try again.");
        }
    }


    return (
        <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
            
            <form className="bg-white p-5 rounded shadow" style={{ width: "400px", maxWidth: "90%" } } onSubmit={handleSubmit}>
                <h2 className="text-center mb-4">User Registration</h2>
                <div className="mb-3">
                <label htmlFor="formUserFirstName" className="form-label">First Name</label>
                <input
                    type="text"
                    className="form-control"
                    id="formUserFirstName"
                    placeholder="First Name"
                    onChange={(e) => setFormUserFirstName(e.target.value)}
                    required
                />
                </div>
                <div className="mb-3">
                <label htmlFor="formUserLastName" className="form-label">Last Name</label>
                <input
                    type="text"
                    className="form-control"
                    id="formUserLastName"
                    placeholder="Last Name"
                    required
                    onChange={(e) => setFormUserLastName(e.target.value)}
                />
                </div>
                <div className="mb-3">
                <label htmlFor="formUserMiddleName" className="form-label">Middle Name</label>
                <input
                    type="text"
                    className="form-control"
                    id="formUserMiddleName"
                    placeholder="Middle Name"
                    required
                    onChange={(e) => setFormUserMiddleName(e.target.value)}
                />
                </div>
                <div className="mb-3">
                <label htmlFor="formUserName" className="form-label">Username</label>
                <input
                    type="text"
                    className="form-control"
                    id="formUserName"
                    placeholder="Username"
                    required
                    onChange={(e) => setFormUserName(e.target.value)}
                />
                </div>
                <div className="mb-3">
                    <label htmlFor="formUserDareOfBirth" className="form-label">Date of Birth</label>
                    <input
                        type="date"
                        className="form-control"
                        id="formUserDareOfBirth"
                        placeholder="YYYY-MM-DD"
                        required
                        onChange={(e) => setFormUserDateOfBirth(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                <label htmlFor="formUserBio" className="form-label">Bio</label>
                <input
                    type="text"
                    className="form-control"
                    id="formUserBio"
                    placeholder="Bio"
                    required
                    onChange={(e) => setFormUserBio(e.target.value)}
                />
                </div>
                <div className="mb-3">
                <label htmlFor="formUserPassword" className="form-label">Password</label>
                <input
                    type="password"
                    className="form-control"
                    id="formUserPassword"
                    placeholder="Password"
                    required
                    onChange={(e) => setFormUserPassword(e.target.value)}
                />
                </div>
                <button type="submit" className="btn btn-primary w-100 mt-3">Submit</button>
            </form>
        </div>
    );
}

export default RegisterUser;