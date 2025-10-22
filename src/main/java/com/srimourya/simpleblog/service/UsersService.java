package com.srimourya.simpleblog.service;

import com.srimourya.simpleblog.dto.RegistrationResponseDTO;
import com.srimourya.simpleblog.dto.UserRegistrationDTO;
import com.srimourya.simpleblog.interfaces.ServiceResponseInterface;
import com.srimourya.simpleblog.models.PersonModel;
import com.srimourya.simpleblog.models.ServiceResponse;
import com.srimourya.simpleblog.models.UsersModel;
import com.srimourya.simpleblog.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService implements ServiceResponseInterface {
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private PersonService personService;

    public UsersModel createUser(UserRegistrationDTO userRegistrationDTO) {
        UsersModel usersModel = new UsersModel();
        usersModel.setUserID(UUID.randomUUID());
        usersModel.setUsername(userRegistrationDTO.getUsername());
        usersModel.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        return userDetailsRepo.save(usersModel);
    }

    public ServiceResponse getUserProfile(UUID userID) {
        Optional<UsersModel> usersModel = userDetailsRepo.findById(userID);
        if (usersModel.isPresent()) {
            UsersModel user = usersModel.get();
            PersonModel personModel = personService.getPersonByUser(user);
            if (personModel != null) {
                RegistrationResponseDTO response =new RegistrationResponseDTO(
                        user.getUserID()
                        , user.getUsername()
                        , personModel.getPersonFirstName()
                        , personModel.getPersonLastName()
                        , personModel.getPersonMiddleName()
                        , personModel.getPersonBio()
                        ,personModel.getPersonDateOfBirth()
                );
                return new ServiceResponse().success(response);
            }
        }
        return new ServiceResponse().failure("userId or Person not found", null, HttpStatus.NOT_FOUND );

    }

    public ServiceResponse updateUser(RegistrationResponseDTO registrationResponseDTO) {
        String failureMessage = null;
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<UsersModel> usersModel = userDetailsRepo.findById(registrationResponseDTO.getUserId());
        if (usersModel.isPresent()) {
            UsersModel user = usersModel.get();
            PersonModel personModel = personService.getPersonByUser(user);
            if (personModel != null) {
                personModel.setPersonBio(registrationResponseDTO.getBio());
                personModel.setPersonFirstName(registrationResponseDTO.getFirstName());
                personModel.setPersonLastName(registrationResponseDTO.getLastName());
                personModel.setPersonMiddleName(registrationResponseDTO.getMiddleName());
                personModel.setPersonBio(registrationResponseDTO.getBio());
                personModel.setPersonDateOfBirth(registrationResponseDTO.getDateOfBirth());
                personService.updatePerson(personModel);
                return serviceResponse.success(registrationResponseDTO);
            }
            else failureMessage = "person not found";
        }
        else{
            failureMessage = "User not found";
        }
        return  serviceResponse.failure(failureMessage, null, HttpStatus.NOT_FOUND );
    }

    protected UsersModel saveUser(UsersModel usersModel) {
        return userDetailsRepo.save(usersModel);
    }
    protected UsersModel getUserByUsername(String username) {
        return userDetailsRepo.findByUsername(username);
    }

    protected UsersModel getUserByUserID(UUID userID) {
        Optional<UsersModel> usersModel  = userDetailsRepo.findById(userID);
        return usersModel.orElse(null);
    }

    protected boolean isUserExists(String username) {
        return userDetailsRepo.findByUsername(username) != null;
    }


}
