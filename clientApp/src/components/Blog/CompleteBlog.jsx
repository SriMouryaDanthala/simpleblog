import { useNavigate, useParams } from "react-router";
import {useState, useEffect} from "react";

const CompleteBlog = () => {
    const navigate = useNavigate();
    const { blogID } = useParams();
    const [currentBlog, setCurrentBlog] = useState(
        {
            blogID : "",
            blogTitle : "",
            blogSummary : "",
            blogContent : "",
            blogAuthorName : "",
            blogAuthorID : ""
        }
    );

    const navigateToAuthorProfile = () => {
        navigate("/profile/"+currentBlog.blogAuthorID);
    }
    const fetchBlogDetails = async (blogID) => {
        try{
            const response = await fetch("http://localhost:8080/"+"blog/getBlog/"+blogID, {
                method : "GET",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization" : "Bearer " + localStorage.getItem("jwt"),
                },
            });  
            if(response.ok){    
                const blogData = await response.json();
                setCurrentBlog(blogData.data);
            }
        }
        catch(err){
            console.log("Error fetching blog details: ", err);
            alert("Failed to fetch blog details. Please try again.");
        }
    };
    useEffect(() =>{
        fetchBlogDetails(blogID);
                    
    },[blogID]);

    return(
         <div
            className="d-flex justify-content-center align-items-start mt-5"
            style={{ minHeight: "80vh" }}
        >
            <div
                className="card shadow-sm p-4"
                style={{
                width: "70%",
                maxWidth: "900px",
                borderRadius: "12px",
                }}
            >
                <div className="card-body">

                <h3 className="card-title fw-bold mb-3 text-center">
                    {currentBlog.blogTitle || 'Blog Title'}
                </h3>


                <p className="fst-italic text-muted text-center mb-4">
                    {currentBlog.blogSummary || 'Blog Summary'}
                </p>


                <p className="card-text fs-5">{currentBlog.blogContent || "Blog Content"}</p>


                <p className="text-end text-secondary mt-4 mb-0">
                    <small onClick={navigateToAuthorProfile} style={{cursor : 'pointer'}}>
                    Written by 
                    <span className="fw-semibold text-dark">{" " + (currentBlog.blogAuthorName || "Author")}</span>
                    </small>
                </p>
                </div>
            </div>
        </div>
    );
};

export default CompleteBlog;