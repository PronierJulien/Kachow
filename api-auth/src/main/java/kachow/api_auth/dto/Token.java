package kachow.api_auth.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tokens")
public class Token {
    @Id
    private UUID id =  UUID.randomUUID();
    private String token;
    private String username;
    private LocalDateTime expirationDate;

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setExpirationDate(LocalDateTime plusHours) {
        this.expirationDate = plusHours;
    }

    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public String getUsername() {
        return this.username;
    }
}