package com.srimourya.simpleblog.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "Blogs")
public class BlogModel {
    @Id
    @Column(name = "blog_id")
    private UUID blogID;
    @Column(name = "blog_title")
    private String blogTitle;
    @Column(name = "blog_name")
    private String blogContent;
    @Column(name = "blog_content")
    private String blogSummary;

    @ManyToOne()
    @JoinColumn(name = "blog_author" , nullable = false)
    private UsersModel blogAuthor;

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

    public void setBlogAuthor(UsersModel blogAuthor) {
        this.blogAuthor = blogAuthor;
    }
}
