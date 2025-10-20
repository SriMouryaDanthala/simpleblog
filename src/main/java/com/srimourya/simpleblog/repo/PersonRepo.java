package com.srimourya.simpleblog.repo;

import com.srimourya.simpleblog.models.PersonModel;
import com.srimourya.simpleblog.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepo extends JpaRepository<PersonModel, UUID> {
    PersonModel findByUsersModel(UsersModel usersModel);
}
