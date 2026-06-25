package com.project.mil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mil.domain.user.AuthenticationDTO;
import com.project.mil.domain.user.LoginResponseDTO;
import com.project.mil.domain.user.RegisterDTO;
import com.project.mil.domain.user.User;
import com.project.mil.infra.security.TokenService;
import com.project.mil.repository.user.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
 
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO request) {
        if (this.userRepository.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(request.username(), request.email(), encryptedPassword, request.role());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
    
}
