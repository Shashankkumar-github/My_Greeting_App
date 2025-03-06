package com.bridgelabz.My_Greeting_App.controller;

import com.bridgelabz.My_Greeting_App.dto.AuthUserDTO;
import com.bridgelabz.My_Greeting_App.dto.LoginDTO;
import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import com.bridgelabz.My_Greeting_App.service.EmailService;
import com.bridgelabz.My_Greeting_App.service.MyGreetingAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public class MyGreetingAppController {
    private final EmailService emailService;

    private final MyGreetingAppService myGreetingAppService;
    @PostMapping("/register")
    public String register(@Valid @RequestBody AuthUserDTO userDTO) {
        return myGreetingAppService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO loginDTO) {
        return myGreetingAppService.loginUser(loginDTO);
    }
    @GetMapping("/swagger")
    public String greet() {
        return "Hello, Swagger!";
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<MyGreetingApp> getGreetingById(@PathVariable Long id) {
        Optional<MyGreetingApp> greeting = myGreetingAppService.findGreetingById(id);
        return greeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/get/save")
    public ResponseEntity<MyGreetingApp> saveGreeting(@RequestParam String message) {
        MyGreetingApp savedGreeting = myGreetingAppService.saveGreeting(message);
        return ResponseEntity.ok(savedGreeting);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<MyGreetingApp>> getAllGreetings() {
        List<MyGreetingApp> greetings = myGreetingAppService.getAllGreetings();
        return ResponseEntity.ok(greetings);
    }
    @PutMapping("/get/{id}")
    public ResponseEntity<MyGreetingApp> updateGreeting(@PathVariable Long id, @RequestParam String newMessage) {
        Optional<MyGreetingApp> updatedGreeting = myGreetingAppService.updateGreeting(id, newMessage);
        return updatedGreeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/get/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        boolean deleted = myGreetingAppService.deleteGreeting(id);
        if (deleted) {
            return ResponseEntity.ok("Greeting deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<Map<String, String>> forgotPassword(
            @PathVariable String email,
            @RequestBody Map<String, String> request) {

        String newPassword = request.get("password");
        String message = emailService.forgotPassword(email, newPassword);
        return ResponseEntity.ok(Map.of("message", message));
    }
    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<Map<String, String>> resetPassword(
            @PathVariable String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        String message = emailService.resetPassword(email, currentPassword, newPassword);
        return ResponseEntity.ok(Map.of("message", message));
    }
}


