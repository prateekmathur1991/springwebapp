package com.springwebapp.authentication.impl;

import com.springwebapp.authentication.TokenManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic concrete implementation of a {@link com.springwebapp.authentication.TokenManager}
 *
 * It maps between Tokens and UserDetails
 */
@Component
public class SimpleTokenManager implements TokenManager {

    private Map<String, UserDetails> users = new HashMap<>();

    private SecureRandom secureRandom = new SecureRandom();

    @Override
    public String createNewToken(UserDetails userDetails) {
        String token;
        do {
            token = generateRandomToken();
        } while (users.containsKey(token));

        UserDetails previousUser = users.put(token, userDetails);
        if (previousUser != null)   {
            System.err.println(" The same token was generated again!!!");
            return null;
        }

        return token;
    }

    @Override
    public UserDetails getUserByToken(String token) {
        return users.get(token);
    }

    @Override
    public UserDetails removeUser(String token) {
        return users.remove(token);
    }

    private String generateRandomToken()    {
        byte [] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return new String(Base64.encode(bytes),StandardCharsets.UTF_8);
    }
}
