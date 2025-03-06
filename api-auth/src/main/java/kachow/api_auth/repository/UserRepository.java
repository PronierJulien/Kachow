package kachow.api_auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kachow.api_auth.dto.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}