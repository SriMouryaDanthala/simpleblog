package com.srimourya.simpleblog.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.UUID;

@Entity(name = "Person")
public class PersonModel {
    @Id
    @Column(name = "Person_id")
    private UUID personID;

    @Column(name = "Person_firstname")
    private String personFirstName;

    @Column(name = "Person_middlename")
    private String personMiddleName;

    @Column(name = "Person_lastname")
    private String personLastName;

    @Column(name = "Person_dateofbirth")
    private Date personDateOfBirth;

    @Column(name = "Person_bio")
    private String personBio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Person_userid", referencedColumnName = "Users_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersModel usersModel;

    public PersonModel(){

    }

    public UUID getPersonID() {
        return personID;
    }

    public void setPersonID(UUID personID) {
        this.personID = personID;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonMiddleName() {
        return personMiddleName;
    }

    public void setPersonMiddleName(String personMiddleName) {
        this.personMiddleName = personMiddleName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Date getPersonDateOfBirth() {
        return personDateOfBirth;
    }

    public void setPersonDateOfBirth(Date personDateOfBirth) {
        this.personDateOfBirth = personDateOfBirth;
    }

    public String getPersonBio() {
        return personBio;
    }

    public void setPersonBio(String personBio) {
        this.personBio = personBio;
    }

    public UsersModel getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(UsersModel usersModel) {
        this.usersModel = usersModel;
    }
}
