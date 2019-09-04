package com.epam.news;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
    String password = "user";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);
        System.out.println(hash);
    }
}
