package com.srimourya.simpleblog.dto;

import java.util.UUID;

public class BlogCardDTO {
    private UUID blogId;
    private String blogTitle;
    private String blogSummary;
    private int totalBogs;

    public UUID getBlogId() {
        return blogId;
    }

    public void setBlogId(UUID blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public int getTotalBogs() {
        return totalBogs;
    }

    public void setTotalBogs(int totalBogs) {
        this.totalBogs = totalBogs;
    }

    public BlogCardDTO(UUID blogId, String blogTitle, String blogSummary) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogSummary = blogSummary;
    }

}
