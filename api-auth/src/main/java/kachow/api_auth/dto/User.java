package kachow.api_auth.dto;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public class User {
    @MongoId
    private UUID id;
    private String username;
    private String password;

    public String getPassword() {
        return this.password;
    }
}