package kachow.api_auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kachow.api_auth.dto.Token;
import kachow.api_auth.dto.User;
import kachow.api_auth.repository.TokenRepository;
import kachow.api_auth.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            String token = generateToken(username);
            Token authToken = new Token();
            authToken.setToken(token);
            authToken.setUsername(username);
            authToken.setExpirationDate(LocalDateTime.now().plusHours(1));
            tokenRepository.save(authToken);
            return token;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }
    
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
    
        String token = generateToken(username);
        Token authToken = new Token();
        authToken.setToken(token);
        authToken.setUsername(username);
        authToken.setExpirationDate(LocalDateTime.now().plusHours(1));
        tokenRepository.save(authToken);
    
        return token;
    }
    

    public String validateToken(String token) {
        Token authToken = tokenRepository.findByToken(token);
        if (authToken != null && authToken.getExpirationDate().isAfter(LocalDateTime.now())) {
            authToken.setExpirationDate(LocalDateTime.now().plusHours(1));
            tokenRepository.save(authToken);
            return authToken.getUsername();
        }
        throw new RuntimeException("Invalid or expired token");
    }

    private String generateToken(String username) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"));
        return username + "-" + dateTime + "-" + UUID.randomUUID().toString();
    }
}
