import { useNavigate } from "react-router";

const MyBlogCard = ({blogData}) => {
    const navigate = useNavigate();
    console.log("Blog Data in MyBlogCard: ", blogData);

    return(
        <div className="card mt-2">
            <div className="card-body">
                <h5 className="card-title">{blogData.blogTitle}</h5>
                <p className="card-text">{blogData.blogSummary}</p>
                <a className="btn btn-primary" onClick = {() =>{navigate('/blog/'+blogData.blogId) }}>Read</a>
            </div>
        </div>
    );
};

export default MyBlogCard;