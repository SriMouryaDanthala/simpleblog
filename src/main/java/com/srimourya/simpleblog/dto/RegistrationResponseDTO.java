package com.srimourya.simpleblog.dto;

import java.util.Date;
import java.util.UUID;

public class RegistrationResponseDTO {
    private UUID userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;
    private String bio;
    private Date dateOfBirth;

    public RegistrationResponseDTO(UUID userId, String userName, String firstName, String lastName, String middleName, String bio, Date dateOfBirth1) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth1;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
