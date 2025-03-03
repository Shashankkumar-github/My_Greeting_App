package com.bridgelabz.My_Greeting_App.controller;

import com.bridgelabz.My_Greeting_App.service.MyGreetingAppService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class MyGreetingAppController {

    private final MyGreetingAppService greetingService;

    public MyGreetingAppController(MyGreetingAppService greetingService) {
        this.greetingService = greetingService;
    }

        @GetMapping
        public Map<String, String> getGreeting() {
            Map<String, String> response = new HashMap<>();
            response.put("message", greetingService.getGreetingMessage());
            return response;
        }


    }


