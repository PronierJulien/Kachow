package com.imt.demo.service;

import com.imt.demo.dao.CarDao;
import com.imt.demo.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    
    private final CarDao carDao;

    public CarService(CarDao cardao) {
        this.carDao = cardao;
    }

    public void saveCar(Car car) {
        carDao.save(car);
    }

    public List<Car> findCarsByBrand(String brand) {
        return carDao.findAllByBrand(brand);
    }
    
}
