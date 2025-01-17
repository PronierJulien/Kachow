package com.imt.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CarJsonDto {
    
    @NotNull private Brand brand;
    @NotBlank private String color;
    @Min(50) private int horsePower;

    public Brand getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public CarJsonDto(Brand brand, String color, int horsePower) {
        this.brand = brand;
        this.color = color;
        this.horsePower = horsePower;
    }
    
}
