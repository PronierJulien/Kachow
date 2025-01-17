package com.imt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Setter
@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @MongoId
    private UUID id;
    private String brand;
    private String color;
    private int horsePower;

    public Car(String brand, String color, int horsePower) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.color = color;
        this.horsePower = horsePower;
    }
    
}
