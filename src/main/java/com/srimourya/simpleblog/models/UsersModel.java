package com.srimourya.simpleblog.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "Users")
public class UsersModel {
    @Id()
    @Column(name = "Users_id")
    private UUID userID;
    @Column(name = "Users_name")
    private String username;
    @Column(name = "Users_Password")
    private String password;
    @OneToOne(mappedBy = "usersModel", cascade = CascadeType.REMOVE, orphanRemoval = true )
    private PersonModel person;

    @OneToMany(mappedBy = "blogAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogModel> blogs;

    @ManyToMany
    @JoinTable(
            name = "User_Blog_Likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id")
    )
    private List<BlogModel> userLikedBlogs;


    public UsersModel() {

    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonModel getPerson() {
        return person;
    }

    public List<BlogModel> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogModel> blogs) {
        this.blogs = blogs;
    }

    public List<BlogModel> getUserLikedBlogs() {
        return userLikedBlogs;
    }

    public void setUserLikedBlogs(List<BlogModel> userLikedBlogs) {
        this.userLikedBlogs = userLikedBlogs;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}
