package com.srimourya.simpleblog.dto;

public class LoginResponseDTO {
    private String jwt;
    private String userID;

    public LoginResponseDTO(String jwt, String userID) {
        this.jwt = jwt;
        this.userID = userID;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
