package com.srimourya.simpleblog.service;

import com.srimourya.simpleblog.dto.UserRegistrationDTO;
import com.srimourya.simpleblog.models.PersonModel;
import com.srimourya.simpleblog.models.UsersModel;
import com.srimourya.simpleblog.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepo _personRepo;

    public PersonModel createPerson(UserRegistrationDTO userRegistrationDTO, UsersModel usersModel) {
        PersonModel person = this.createPersonFromUserRegistrationDTO(userRegistrationDTO);
        person.setUsersModel(usersModel);
        return _personRepo.save(person);
    }

    private PersonModel createPersonFromUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO) {
        PersonModel person = new PersonModel();
        person.setPersonID(UUID.randomUUID());
        person.setPersonFirstName(userRegistrationDTO.getUserFirstName());
        person.setPersonMiddleName(userRegistrationDTO.getUserMiddleName());
        person.setPersonLastName(userRegistrationDTO.getUserLastName());
        person.setPersonDateOfBirth(userRegistrationDTO.getDateOfBirth());
        person.setPersonBio(userRegistrationDTO.getUserBio());
        return person;
    }

    protected PersonModel getPersonByUser(UsersModel user) {
        return  _personRepo.findByUsersModel(user);
    }

    protected PersonModel updatePerson (PersonModel personModel) {
        return _personRepo.save(personModel);
    }
}
