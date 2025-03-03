package com.bridgelabz.My_Greeting_App.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class MyGreetingAppController {


        @GetMapping
        public Map<String, String> getGreeting() {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello, GET request received!");
            return response;
        }

        @PostMapping
        public Map<String, String> postGreeting() {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello, POST request received!");
            return response;
        }

        @PutMapping
        public Map<String, String> putGreeting() {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello, PUT request received!");
            return response;
        }

        @DeleteMapping
        public Map<String, String> deleteGreeting() {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello, DELETE request received!");
            return response;
        }
    }


