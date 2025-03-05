package com.bridgelabz.My_Greeting_App.service;

import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import com.bridgelabz.My_Greeting_App.repository.MyGreetingAppRepository;
import com.bridgelabz.My_Greeting_App.repository.AuthUserRepository;
import com.bridgelabz.My_Greeting_App.service.JwtService;
import com.bridgelabz.My_Greeting_App.dto.AuthUserDTO;
import com.bridgelabz.My_Greeting_App.dto.LoginDTO;
import com.bridgelabz.My_Greeting_App.entity.AuthUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyGreetingAppService {

    private final MyGreetingAppRepository myGreetingAppRepository;
    private final AuthUserRepository authUserRepository;  //  Corrected repository for AuthUser
    private final JwtService jwtService;
    private final EmailService emailService;

    //  Save a new greeting
    public MyGreetingApp saveGreeting(String message) {
        MyGreetingApp greeting = new MyGreetingApp(message);
        return myGreetingAppRepository.save(greeting);
    }

    //  Fetch a greeting by ID
    public Optional<MyGreetingApp> findGreetingById(Long id) {
        return myGreetingAppRepository.findById(id);
    }

    //  Get all greetings
    public List<MyGreetingApp> getAllGreetings() {
        return myGreetingAppRepository.findAll();
    }

    //  Update greeting
    public Optional<MyGreetingApp> updateGreeting(Long id, String newMessage) {
        Optional<MyGreetingApp> greetingOptional = myGreetingAppRepository.findById(id);
        if (greetingOptional.isPresent()) {
            MyGreetingApp greeting = greetingOptional.get();
            greeting.setMessage(newMessage);
            myGreetingAppRepository.save(greeting);
            return Optional.of(greeting);
        }
        return Optional.empty();
    }

    //  Delete greeting
    public boolean deleteGreeting(Long id) {
        if (myGreetingAppRepository.existsById(id)) {
            myGreetingAppRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //  User Registration
    public String registerUser(AuthUserDTO userDTO) {
        if (authUserRepository.findByEmail(userDTO.getEmail()).isPresent()) {  //  Use correct repository
            throw new RuntimeException("Email is already in use.");
        }

        AuthUser user = AuthUser.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .build();

        authUserRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Registration Successful", "Welcome to our platform!");

        return "User registered successfully!";
    }

    //  User Login
    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDTO.getEmail());  //  Use correct repository
        if (user.isEmpty() || !new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        return jwtService.generateToken(user.get().getEmail());
    }
}
