package com.imt.demo.dao;

import com.imt.demo.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarDao extends MongoRepository<Car, UUID> {
    
    List<Car> findAllByBrand(String brand);
    
}
