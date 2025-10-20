package com.srimourya.simpleblog.service;

import com.srimourya.simpleblog.models.UsersModel;
import com.srimourya.simpleblog.models.UsersPrinciple;
import com.srimourya.simpleblog.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetialsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel usersModel = repo.findByUsername(username);
        if(usersModel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UsersPrinciple(usersModel);
    }
}
