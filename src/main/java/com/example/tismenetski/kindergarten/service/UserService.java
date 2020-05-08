package com.example.tismenetski.kindergarten.service;


import com.example.tismenetski.kindergarten.entities.User;
import com.example.tismenetski.kindergarten.exceptions.UsernameAlreadyExistsException;
import com.example.tismenetski.kindergarten.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //Since this is not a bean we need to wire it manually to our application

    //Saving a new user to the database
    public User saveUser(User newUser)
    {
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword())); //set encrypted password for our user
            newUser.setUsername(newUser.getUsername()); //check if the username already exists

            //Make sure that password and confirm password match
            //We don't persist of show confirm password
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username " + newUser.getUsername() + " Already Exists"); //throwing exception ,username already exists
        }
    }
}
