package com.luxoft.spingsecurity.userdetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordGenerator {
    public static void main(String[] args) {
        var inputPassword = args[0];
        var bcryptEncoder = new BCryptPasswordEncoder(10);

        // Everytime generates new string with hash password
        var encoded = bcryptEncoder.encode(inputPassword);
        System.out.println(encoded);
    }
}
