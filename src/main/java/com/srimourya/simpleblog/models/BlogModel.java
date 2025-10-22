package com.srimourya.simpleblog.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "Blogs")
public class BlogModel {
    @Id
    @Column(name = "blog_id")
    private UUID blogID;
    @Column(name = "blog_title")
    private String blogTitle;
    @Column(name = "blog_content", columnDefinition = "TEXT")
    private String blogContent;
    @Column(name = "blog_summary")
    private String blogSummary;

    @ManyToOne()
    @JoinColumn(name = "blog_author" , nullable = false)
    private UsersModel blogAuthor;

    @ManyToMany(mappedBy = "userLikedBlogs")
    private List<UsersModel> likedUsers;

    public UUID getBlogID() {
        return blogID;
    }

    public void setBlogID(UUID blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public UsersModel getBlogAuthor() {
        return blogAuthor;
    }

    public List<UsersModel> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<UsersModel> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public void setBlogAuthor(UsersModel blogAuthor) {
        this.blogAuthor = blogAuthor;
    }
}
