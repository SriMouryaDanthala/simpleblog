
import { use, useEffect, useState } from 'react';
import cardsData from '../../constants/blogDisplayData';
import BlogCard from './BlogCard';
import BlogGridPlaceHolder from '../placeHolder/BlogGridPlaceHolder';
const BlogCardGrid = () =>{

    const cardsPerPage = 8;
    const [blogCards, setBlogCards] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalBlogs, setTotalBogs] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const fetchBlogCardsForPage = async(page,offset) => {
        var response = await fetch(`http://localhost:8080/blog/getBlogCards?page=${page}&offset=${offset}`, {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                "Authorization" : "Bearer " + localStorage.getItem("jwt"),
            },
        });
        if(response.ok){
            const responseJson = await response.json();
            setBlogCards(responseJson.data.blogs);
            setTotalPages(responseJson.data.totalPages);
            setTotalBogs(responseJson.data.totalBogs);
        }
    }

    const goToPage = (pageNumber) => {
        if(pageNumber < 1 || pageNumber > totalPages) return;
        setCurrentPage(pageNumber);
    }

    useEffect(()=>{
        fetchBlogCardsForPage(currentPage, cardsPerPage)
    }, [currentPage])
    

    return blogCards.length == 0 ? <BlogGridPlaceHolder/>: (
       <div className="container mt-4">

            <div className="d-flex flex-wrap justify-content-center">
                {blogCards.map((card) => (
                <BlogCard key={card.blogId} cardData={card} />
                ))}
            </div>

            <div className="d-flex justify-content-between align-items-center mt-4 px-3">
                <button
                className="btn btn-outline-primary"
                disabled={currentPage === 1}
                onClick={() => { goToPage(currentPage - 1); }}
                >
                ← Back
                </button>

                <span className="fw-semibold">
                Page {currentPage} of {totalPages}
                </span>

                <button
                className="btn btn-outline-primary"
                disabled={currentPage === totalPages}
                onClick={() => { goToPage(currentPage + 1); }}
                >
                Next →
                </button>
            </div>
        </div>

    );

};

export default BlogCardGrid;