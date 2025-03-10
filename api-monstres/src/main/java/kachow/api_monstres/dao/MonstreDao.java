package kachow.api_monstres.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kachow.api_monstres.model.Monstre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonstreDao extends MongoRepository<Monstre, UUID> {
    List<Monstre> findAll();
    Optional<Monstre> findById(UUID id);
    void deleteById(UUID id);
}
