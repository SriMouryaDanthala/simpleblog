package com.srimourya.simpleblog.service;

import com.srimourya.simpleblog.dto.LoginResponseDTO;
import com.srimourya.simpleblog.dto.RegistrationResponseDTO;
import com.srimourya.simpleblog.dto.UserLoginDTO;
import com.srimourya.simpleblog.dto.UserRegistrationDTO;
import com.srimourya.simpleblog.models.PersonModel;
import com.srimourya.simpleblog.models.ServiceResponse;
import com.srimourya.simpleblog.models.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UsersService usersService;


    public ServiceResponse verify(UserLoginDTO userLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username, userLogin.password));
        if(authentication.isAuthenticated()){
            String userID  = usersService.getUserByUsername(userLogin.username).getUserID().toString();
            String jwt = jwtService.generateToken(userLogin.username);
            return new ServiceResponse().success(new LoginResponseDTO(jwt, userID));
        }
        else {
            return new ServiceResponse().failure("user not found", null, HttpStatus.UNAUTHORIZED);
        }
    }

    public ServiceResponse register(UserRegistrationDTO userRegistrationDTO){
        try {
            if(!isUserNameValid(userRegistrationDTO.getUsername())){
                throw new Exception("Username is invalid");
            }
            UsersModel usersModel = usersService.createUser(userRegistrationDTO);
            PersonModel person = personService.createPerson(userRegistrationDTO, usersModel);
            var responseDTO =  new RegistrationResponseDTO(usersModel.getUserID(), usersModel.getUsername(), person.getPersonFirstName(), person.getPersonLastName(), person.getPersonMiddleName(), person.getPersonBio(), person.getPersonDateOfBirth());
            return new ServiceResponse().success(responseDTO);
        }
        catch (Exception e){
            return new ServiceResponse().failure("register failed " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected boolean isUserNameValid(String userName){
        return !usersService.isUserExists(userName);
    }
}
