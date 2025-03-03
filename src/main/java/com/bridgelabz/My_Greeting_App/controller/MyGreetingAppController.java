package com.bridgelabz.My_Greeting_App.controller;

import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import com.bridgelabz.My_Greeting_App.service.MyGreetingAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class MyGreetingAppController {

    private final MyGreetingAppService myGreetingAppService;

    public MyGreetingAppController(MyGreetingAppService myGreetingAppService) {
        this.myGreetingAppService = myGreetingAppService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyGreetingApp> getGreetingById(@PathVariable Long id) {
        Optional<MyGreetingApp> greeting = myGreetingAppService.findGreetingById(id);
        return greeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public ResponseEntity<MyGreetingApp> saveGreeting(@RequestParam String message) {
        MyGreetingApp savedGreeting = myGreetingAppService.saveGreeting(message);
        return ResponseEntity.ok(savedGreeting);
    }
    @GetMapping("/all")
    public ResponseEntity<List<MyGreetingApp>> getAllGreetings() {
        List<MyGreetingApp> greetings = myGreetingAppService.getAllGreetings();
        return ResponseEntity.ok(greetings);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MyGreetingApp> updateGreeting(@PathVariable Long id, @RequestParam String newMessage) {
        Optional<MyGreetingApp> updatedGreeting = myGreetingAppService.updateGreeting(id, newMessage);
        return updatedGreeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

