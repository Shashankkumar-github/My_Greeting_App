package com.bridgelabz.My_Greeting_App.service;

import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import com.bridgelabz.My_Greeting_App.repository.MyGreetingAppRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyGreetingAppService {

    private final MyGreetingAppRepository myGreetingAppRepository;

    public MyGreetingAppService(MyGreetingAppRepository myGreetingAppRepository) {
        this.myGreetingAppRepository = myGreetingAppRepository;
    }

    public MyGreetingApp saveGreeting(String message) {
        MyGreetingApp greeting = new MyGreetingApp(message);
        return myGreetingAppRepository.save(greeting);
    }

    public Optional<MyGreetingApp> findGreetingById(Long id) {
        return myGreetingAppRepository.findById(id);
    }
    public List<MyGreetingApp> getAllGreetings() {
        return myGreetingAppRepository.findAll();
    }
}
