package com.bridgelabz.My_Greeting_App.model;
import jakarta.persistence.*;
@Entity
@Table(name = "greetings")
public class MyGreetingApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        private String message;

        public MyGreetingApp() {
        }

        public MyGreetingApp(String message) {
            this.message = message;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



