import { use, useEffect, useState } from "react";
import BlogGridPlaceHolder from "../placeHolder/BlogGridPlaceHolder";
import MyBlogCard from "./MyBlogCard";

const MyBlogs = () => {
    const [userBlogs,setUserBlogs] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [isLoading, setIsLoading] = useState(true);
    const cardsPerPage = 5;

    const fetchUserBlogs = async(page, offset) => {
        setIsLoading(true);
        try{
            const response = await fetch(`http://localhost:8080/blog/getUserBlogs?page=${page}&offset=${offset}&authorID=${localStorage.getItem("currentUserID")}`, {
                method : "GET",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization" : "Bearer " + localStorage.getItem("jwt"),
                },
            });
            if(response.ok){    
                const blogsData = await response.json();
                setUserBlogs(blogsData.data.blogs || []);
                setTotalPages(blogsData.data.totalPages || 0);
                console.log(userBlogs);
            }
            else{
                alert("Failed to fetch user blogs. Please try again.");
            }
        }
        catch(err){
            alert("Failed to fetch user blogs. Please try again.");
            console.log("Error fetching user blogs: ", err);
        }
        finally{
            setIsLoading(false);
        }
    }

    useEffect( ()=>{
        fetchUserBlogs(currentPage, cardsPerPage);
    }, [currentPage]);
    return (isLoading  ? <> </> : userBlogs.length === 0 ? <BlogGridPlaceHolder/> :  (
        <div className="d-flex flex-column align-items-center justify-content-center vh-70">
            <div className="w-50">
                {userBlogs.map((blog, index) => (<MyBlogCard key={blog.blogID || index} blogData={blog} />))}
            </div>
            <div className="d-flex justify-content-between align-items-center w-50 mt-4">
                <button
                className="btn btn-outline-primary"
                onClick={() => setCurrentPage(currentPage - 1)}
                disabled={currentPage === 1}
                >
                ⬅ Back
                </button>

                <span className="text-muted">
                {currentPage} of {totalPages}
                </span>

                <button
                className="btn btn-primary"
                onClick={()=> setCurrentPage(currentPage + 1)}
                disabled={currentPage === totalPages}
                >
                Next ➡
                </button>
            </div>
        </div>
    ));
};

export default MyBlogs;