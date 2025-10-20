import { useState } from "react";

const NewBlog = () =>{
    const [blogData, setBlogData] = useState({
        blogTitle : "",
        blogSummary : "",
        blogContent : "",
        author : localStorage.getItem("currentUserID")
    });

    const resetForm = () => setBlogData({
        blogTitle: "",
        blogSummary: "",
        blogContent: "",
        author: localStorage.getItem("currentUserID")
    });

    const onBlogContentChange = (e) => {
        const { name, value } = e.target;
        setBlogData((prevData) => ({...prevData, [name]: value }));
    };

    const onSubmitHandler = async (e) => {
        e.preventDefault();
        try{
            const response = await fetch("http://localhost:8080/blog/create", {
                method : "POST",
                headers : {
                    "Content-Type" : "application/json",
                    "Authorization" : "Bearer " + localStorage.getItem("jwt"),
                },
                body : JSON.stringify(blogData)
            });
            if(response.ok){
                alert("Blog saved successfully!");
            }
            else{
                alert("Failed to save blog. Please try again.");
                console.log(response)
                return;
            }
        }
        catch(err){
            console.log("Error while saving blog: ", err);
            alert("Failed to save blog. Please try again.");
            return;
        }
        resetForm();
    };

    return(
        <div className="container mt-5" style={{ maxWidth: "700px" }}>
            <h3 className="text-center mb-4">Create New Blog</h3>
            
            <form onSubmit={onSubmitHandler}>
                <div className="mb-3">
                <label htmlFor="blogTitle" className="form-label">Title</label>
                <input
                    type="text"
                    className="form-control"
                    id="blogTitle"
                    placeholder="Enter your blog title"
                    value={blogData.blogTitle}
                    onChange={onBlogContentChange}
                    name="blogTitle"
                />
                </div>

                <div className="mb-3">
                <label htmlFor="blogSummary" className="form-label">Summary</label>
                <input
                    type="text"
                    className="form-control"
                    id="blogSummary"
                    value={blogData.blogSummary}
                    onChange={onBlogContentChange}
                    placeholder="A short summary of your blog"
                    name="blogSummary"
                />
                </div>

                <div className="mb-3">
                <label htmlFor="blogContent" className="form-label">Main Content</label>
                <textarea
                    className="form-control"
                    id="blogContent"
                    rows="8"
                    value={blogData.blogContent}
                    onChange={onBlogContentChange}
                    placeholder="Write your blog here..."
                    name="blogContent"
                ></textarea>
                </div>

                <div className="text-center">
                <button type="submit" className="btn btn-primary px-4">
                    Submit
                </button>
                </div>

            </form>
        </div>

    );
};

export default NewBlog;