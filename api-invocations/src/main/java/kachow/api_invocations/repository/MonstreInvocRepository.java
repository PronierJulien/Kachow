package kachow.api_invocations.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import kachow.api_invocations.dto.MonstreInvocDTO;

public interface MonstreInvocRepository extends MongoRepository<MonstreInvocDTO, UUID> {
    Optional findById(UUID id);
}