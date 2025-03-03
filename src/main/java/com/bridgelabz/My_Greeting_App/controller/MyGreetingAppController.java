package com.bridgelabz.My_Greeting_App.controller;

import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import com.bridgelabz.My_Greeting_App.service.MyGreetingAppService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class MyGreetingAppController {

    private final MyGreetingAppService myGreetingAppService;

    public MyGreetingAppController(MyGreetingAppService myGreetingAppService) {
        this.myGreetingAppService = myGreetingAppService;
    }

    @GetMapping("/all")
    public List<MyGreetingApp> getAllGreetings() {
        return myGreetingAppService.getAllGreetings();
    }

    @PostMapping("/save")
    public MyGreetingApp saveGreeting(@RequestParam String message) {
        return myGreetingAppService.saveGreeting(message);
    }
}
