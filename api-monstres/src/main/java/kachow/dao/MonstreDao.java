package kachow.dao;

import kachow.model.Monstre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonstreDao extends MongoRepository<Monstre, UUID> {
    List<Monstre> findAll();
    Optional<Monstre> findById(UUID id);
    void deleteById(UUID id);
}
