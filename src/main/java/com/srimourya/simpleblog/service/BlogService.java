package com.srimourya.simpleblog.service;

import com.srimourya.simpleblog.dto.BlogCardDTO;
import com.srimourya.simpleblog.dto.BlogCreationDTO;
import com.srimourya.simpleblog.dto.BlogResponseDTO;
import com.srimourya.simpleblog.models.BlogModel;
import com.srimourya.simpleblog.models.ServiceResponse;
import com.srimourya.simpleblog.models.UsersModel;
import com.srimourya.simpleblog.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogService {

    @Autowired
    UsersService usersService;
    @Autowired
    BlogRepo blogRepo;


    public ServiceResponse createNewBlog(BlogCreationDTO blogCreationDTO) {
        String failureMessage = null;
        Exception exception = null;
        ServiceResponse serviceResponse = new ServiceResponse();
        UsersModel blogAuthor = usersService.getUserByUserID(blogCreationDTO.getAuthor());
        if(blogAuthor != null) {
            try {
                BlogModel blogModel = new BlogModel();
                blogModel.setBlogAuthor(blogAuthor);
                blogModel.setBlogContent(blogCreationDTO.getBlogContent());
                blogModel.setBlogTitle(blogCreationDTO.getBlogTitle());
                blogModel.setBlogSummary(blogCreationDTO.getBlogSummary());
                blogModel.setBlogID(UUID.randomUUID());
                blogRepo.save(blogModel);
                BlogCardDTO blogCardDTO = new BlogCardDTO(blogModel.getBlogID(), blogModel.getBlogTitle(), blogModel.getBlogSummary());
                return serviceResponse.success(blogCardDTO);
            }
            catch (Exception e) {
                exception = e;
                failureMessage = e.getMessage();
            }
        }
        else {
            failureMessage = "User not found";
        }
        return serviceResponse.failure(failureMessage, exception, exception == null ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ServiceResponse getBlogCards(int pageNumber, int blogsPerPage){
        long totalBlogs = this.getTotalCountOfBlogs();
        List<BlogCardDTO> blogsList = new ArrayList<>();
        int totalPages = 0;
        Map<String, Object> returnMap = new HashMap<>();
        if(totalBlogs > 0) {
            Pageable pageable = PageRequest.of(pageNumber-1, blogsPerPage, Sort.by("blogTitle").ascending());
            Page<BlogCardDTO> pagedBlogs = blogRepo.findAllBlogSummaries(pageable);
            if(pagedBlogs.getTotalElements() > 0) {
                blogsList = pagedBlogs.getContent();
                totalPages = pagedBlogs.getTotalPages();
                Map<String, Object> returnValues = new HashMap<>();
            }
        }
        returnMap.put("blogs", blogsList);
        returnMap.put("totalBlogs", totalBlogs);
        returnMap.put("totalPages",totalPages);
        return new ServiceResponse().success(returnMap);
    }

    public ServiceResponse getBlogByBlogId(UUID blogId){
        BlogResponseDTO blogResponseDTO = new BlogResponseDTO();
        Optional<BlogModel> blogModel  = blogRepo.findById(blogId);
        if(blogModel.isPresent()) {
            BlogModel blog = blogModel.get();
            UsersModel blogAuthor = blog.getBlogAuthor();
            blogResponseDTO.setBlogAuthorID(blogAuthor.getUserID());
            blogResponseDTO.setBlogContent(blog.getBlogContent());
            blogResponseDTO.setBlogTitle(blog.getBlogTitle());
            blogResponseDTO.setBlogSummary(blog.getBlogSummary());
            blogResponseDTO.setBlogID(blog.getBlogID());
            blogResponseDTO.setBlogAuthorName(blogAuthor.getUsername());
            return new ServiceResponse().success(blogResponseDTO);
        }
        else{
            return new ServiceResponse().failure("Blog Not found", null, HttpStatus.NOT_FOUND);
        }
    }

    private long  getTotalCountOfBlogs(){
        return blogRepo.count();
    }
}
