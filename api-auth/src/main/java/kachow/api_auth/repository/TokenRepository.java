package kachow.api_auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kachow.api_auth.dto.Token;

public interface TokenRepository extends MongoRepository<Token, String> {
    Token findByToken(String token);
}