package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dto.AuthRequest;
import com.oktenweb.javaadvanced.dto.AuthenticationResponse;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.service.IUserService;
import com.oktenweb.javaadvanced.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

// Цей контролер не має жодного відношення до автентифікації. Нею повністю займається Spring

public class UserController {

  @Autowired
  private IUserService userService;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  // реєстрація юзера
  @PostMapping
  public String registerUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse generateJWT(@RequestBody AuthRequest authRequest) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    return new AuthenticationResponse(jwtService.generateToken(authRequest.getUsername()));
  }
}
