package com.bridgelabz.My_Greeting_App.repository;

import com.bridgelabz.My_Greeting_App.model.MyGreetingApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


    @Repository
    public interface MyGreetingAppRepository extends JpaRepository<MyGreetingApp, Long> {
    }

