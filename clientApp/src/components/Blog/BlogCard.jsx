import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const BlogCard = (cardData) => {
  const navigate = useNavigate();
  const {blogId, blogTitle,  blogSummary} = cardData.cardData;
  const launchBlog = () => {
    navigate("/blog/"+blogId);
  }
  return (
    <div className="card " style={{ width: "18rem", padding: "10px", margin: "10px" }} key={blogId}>
      <div className="card-body">
        <h5 className="card-title">{blogTitle}</h5>
        <p className="card-text">
          {blogSummary}
        </p>
        <button onClick={launchBlog} className="btn btn-primary">
          Read
        </button>
      </div>
    </div>
  );
};

export default BlogCard;
