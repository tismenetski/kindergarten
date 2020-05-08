package com.example.tismenetski.kindergarten.service;

import com.example.tismenetski.kindergarten.entities.User;
import com.example.tismenetski.kindergarten.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //Here we override the default loadUserByUsername provided by UserDetailsService. Extract user from userRepository by username, in null throw exception,otherwise return the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null)  new UsernameNotFoundException("User not found");
        return user;
    }

    //Extract user from userRepository by id, in null throw exception,otherwise return the user
    @Transactional
    public User loadUserById(Long id)
    {
        User user = userRepository.getById(id);
        if (user==null)  new UsernameNotFoundException("User not found");
        return user;
    }
}
