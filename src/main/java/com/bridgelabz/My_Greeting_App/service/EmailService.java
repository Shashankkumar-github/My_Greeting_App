package com.bridgelabz.My_Greeting_App.service;


import com.bridgelabz.My_Greeting_App.entity.AuthUser;
import com.bridgelabz.My_Greeting_App.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final AuthUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public String forgotPassword(String email, String newPassword) {
        Optional<AuthUser> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return "Sorry! We cannot find the user email: " + email;
        }

        AuthUser user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        sendEmail(email, "Password Changed", "Your password has been successfully changed.");

        return "Password has been changed successfully!";
    }


    @Transactional
    public String resetPassword(String email, String currentPassword, String newPassword) {
        Optional<AuthUser> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return "User not found with email: " + email;
        }

        AuthUser user = optionalUser.get();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return "Current password is incorrect!";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset successfully!";
    }

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
