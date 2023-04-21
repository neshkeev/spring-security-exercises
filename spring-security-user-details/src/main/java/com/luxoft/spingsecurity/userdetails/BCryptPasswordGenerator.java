package com.luxoft.spingsecurity.userdetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class BCryptPasswordGenerator {
    public static void main(String[] args) {
        System.out.print("Enter a password to encode: ");
        final var scanner = new Scanner(System.in);
        final var inputPassword = scanner.nextLine();
        final var bcryptEncoder = new BCryptPasswordEncoder(10);

        // Everytime generates new string with hash password
        final var encoded = bcryptEncoder.encode(inputPassword);
        System.out.println(encoded);
    }
}
