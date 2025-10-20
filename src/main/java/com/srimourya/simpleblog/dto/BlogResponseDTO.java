package com.srimourya.simpleblog.dto;

import java.util.UUID;

public class BlogResponseDTO {
    private UUID blogID;
    private String blogTitle;
    private UUID blogAuthorID;
    private String blogAuthorName;
    private String blogSummary;
    private String blogContent;

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

    public UUID getBlogAuthorID() {
        return blogAuthorID;
    }

    public void setBlogAuthorID(UUID blogAuthorID) {
        this.blogAuthorID = blogAuthorID;
    }

    public String getBlogAuthorName() {
        return blogAuthorName;
    }

    public void setBlogAuthorName(String blogAuthorName) {
        this.blogAuthorName = blogAuthorName;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}
