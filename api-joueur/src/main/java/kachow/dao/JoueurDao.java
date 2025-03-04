package kachow.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kachow.model.Joueur;

@Repository
public interface JoueurDao extends MongoRepository<Joueur, UUID> {
    List<Joueur> findAll();
    Optional<Joueur> findById(UUID id);
    void deleteById(UUID id);
}
