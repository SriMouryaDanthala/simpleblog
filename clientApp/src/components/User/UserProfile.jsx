import { use, useEffect, useState } from "react";
import { useParams } from "react-router";

const UserProfile = () =>{
    const { userID } = useParams();
    const [formEnabled, setFormEnabled] = useState(false);
    const [formData, setFormData] = useState({
        firstName : "",
        middleName : "",
        lastName : "",
        bio : "",
        dateOfBirth : "",
        userName : "",
        userID : ""
    });

    const onFormFieldChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const fetchUserProfileAndUpdate = async (userID) => {
            try{
                const response = await fetch("http://localhost:8080/"+"user/"+userID, {
                    method : "GET",
                    headers : {
                        "Content-Type" : "application/json",
                        "Authorization" : "Bearer " + localStorage.getItem("jwt"),
                    },
                    
                });  
                if(response.ok){    
                    const userData = await response.json();
                    setFormData({
                        firstName : userData.data.firstName || "",
                        middleName : userData.data.middleName || "",
                        lastName : userData.data.lastName || "",
                        bio : userData.data.bio || "",
                        dateOfBirth : userData.data.dateOfBirth? userData.data.dateOfBirth.split("T")[0] : "",
                        userName : userData.data.userName || "",
                        userID : userData.data.userID || "",
                    });
                }
            }
            catch(err){
                console.log("Error fetching user profile: ", err);
            }
    };

    const submitProfileUpdates = async (e) => {
        e.preventDefault();
        try{
            const response = await fetch("http://localhost:8080/"+"user", {
                method : "PUT",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization" : "Bearer " + localStorage.getItem("jwt"),
                },
                body : JSON.stringify({
                    ...formData,
                    userId : userID
                })
            });  

            if(response.ok){    
                alert("Profile updated successfully!");
                fetchUserProfileAndUpdate(userID);
            }
            else{
                alert("Failed to update profile. Please try again.");
            }
        }
        catch(err){
        }
    };

    useEffect(() => {
        fetchUserProfileAndUpdate(userID);
    }, [userID]);

    return (
        <div className="d-flex justify-content-center align-items-start vh-1000" style={{ minHeight: "100vh", marginTop: "80px" }}>
            <div className="card shadow p-4" style={{ width: "50%" }}>
                <h4 className="mb-4 text-center">User Info</h4>
                <form onSubmit={async (e) => {submitProfileUpdates(e);}}>
                <div className="row g-3">
                    <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        id="profileFirstName"
                        placeholder="First name"
                        aria-label="First name"
                        name="firstName"
                        value={formData.firstName || ""}
                        onChange={onFormFieldChange}
                        disabled = {!formEnabled}
                    />
                    <small className="form-text text-muted mt-1">First Name</small>
                    </div>
                    <div className="col-md-6">
                    <input
                        type="text"
                        id="profileMiddleName"
                        className="form-control"
                        placeholder="Middle name"
                        aria-label="Middle name"
                        name="middleName"
                        value={formData.middleName || ""}
                        onChange={onFormFieldChange}
                        disabled = {!formEnabled}
                    />
                    <small className="form-text text-muted mt-1">Middle Name</small>
                    </div>
                    <div className="col-md-6">
                    <input
                        type="text"
                        id="profileLastName"
                        className="form-control"
                        placeholder="Last name"
                        aria-label="Last name"
                        name="lastName"
                        value={formData.lastName || ""}
                        onChange={onFormFieldChange}
                        disabled = {!formEnabled}
                    />
                     <small className="form-text text-muted mt-1">Last Name</small>
                    </div>
                    <div className="col-md-6">
                    <input
                        type="text"
                        id="profileBio"
                        className="form-control"
                        placeholder="Bio"
                        aria-label="Bio"
                        name="bio"
                        value={formData.bio || ""}
                        onChange={onFormFieldChange}
                        disabled = {!formEnabled}
                    />
                     <small className="form-text text-muted mt-1">Bio</small>
                    </div>
                    <div className="col-md-6">
                    <input
                        type="date"
                        id="profileDateOfBirth"
                        className="form-control"
                        placeholder="YYYY-MM-DD"
                        aria-label="Date of Birth"
                        name="dateOfBirth"
                        value={formData.dateOfBirth || ""}
                        onChange={onFormFieldChange}
                        disabled = {!formEnabled}
                    />
                     <small className="form-text text-muted mt-1">Date of birth</small>
                    </div>
                    <div className="col-md-6">
                    <input
                        type="text"
                        id="profileUserName"
                        className="form-control"
                        placeholder="UserName"
                        aria-label="UserName"
                        name="userName"
                        onChange={onFormFieldChange }
                        value={formData.userName || ""}
                        disabled = {true}
                    />
                     <small className="form-text text-muted mt-1">UserName</small>
                    </div>
                </div>

                <div className="mt-4 text-center">
                    <button  hidden = {userID === localStorage.getItem("currentUserID") ? false : true} type="submit" className="btn btn-primary px-4">
                    Save
                    </button>
                </div>
                <div className="mt-4 text-center">
                    <button  hidden = {userID === localStorage.getItem("currentUserID") ? false : true} className="btn btn-primary px-4" type = "button" onClick={() => setFormEnabled(!formEnabled)}>
                    Edit
                    </button>
                </div>
                </form>
            </div>
        </div>
    );
};

export default UserProfile;