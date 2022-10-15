package com.developer.project.springproject.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHash {

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

}
