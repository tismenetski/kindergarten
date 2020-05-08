package com.example.tismenetski.kindergarten.controllers;


import com.example.tismenetski.kindergarten.entities.User;
import com.example.tismenetski.kindergarten.payload.JWTLoginSuccessResponse;
import com.example.tismenetski.kindergarten.payload.LoginRequest;
import com.example.tismenetski.kindergarten.security.JwtTokenProvider;
import com.example.tismenetski.kindergarten.service.MapValidationErrorService;
import com.example.tismenetski.kindergarten.service.UserService;
import com.example.tismenetski.kindergarten.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.tismenetski.kindergarten.security.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap; // Returns the error map with a BAD_REQUEST Status

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user , BindingResult result)
    {
        //Validate password match

        userValidator.validate(user,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap; // Returns the error map with a BAD_REQUEST Status

        User newUser = userService.saveUser(user); //Save The User in the repository

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED); //Return 201(created) response with the user
    }
}
