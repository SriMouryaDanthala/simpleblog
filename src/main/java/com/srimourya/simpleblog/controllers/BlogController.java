package com.srimourya.simpleblog.controllers;

import com.srimourya.simpleblog.dto.BlogCreationDTO;
import com.srimourya.simpleblog.models.ApiResponse;
import com.srimourya.simpleblog.models.ServiceResponse;
import com.srimourya.simpleblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBlog(@RequestBody BlogCreationDTO blogCreationDTO) {
        ServiceResponse response = blogService.createNewBlog(blogCreationDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @GetMapping("/getBlogCards")
    public ResponseEntity<ApiResponse> getBlogCards(@RequestParam int page, @RequestParam int offset) {
        ServiceResponse response = blogService.getBlogCards(page, offset);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @GetMapping("/getBlog/{blogID}")
    public ResponseEntity<ApiResponse> getBlog(@PathVariable UUID blogID) {
        ServiceResponse response = blogService.getBlogByBlogId(blogID);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }

    @GetMapping("/getUserBlogs")
    public ResponseEntity<ApiResponse> getUserBlogs(@RequestParam int page, @RequestParam int offset, @RequestParam UUID authorID) {
        ServiceResponse response = blogService.getBlogCardsOfUser(page, offset, authorID);
        return ResponseEntity.status(response.getHttpStatus()).body(ApiResponse.importAPIResponseFromService(response));
    }
}
