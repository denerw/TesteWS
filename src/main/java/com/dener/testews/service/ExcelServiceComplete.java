package com.dener.testews.service;

import com.dener.testews.helper.ExcelHelperCars;
import com.dener.testews.helper.ExcelHelperComplete;
import com.dener.testews.helper.ExcelHelperFactories;
import com.dener.testews.model.Car;
import com.dener.testews.model.Factory;
import com.dener.testews.repository.CarRepository;
import com.dener.testews.repository.FactoryRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class ExcelServiceComplete {

    @Autowired
    FactoryRepository factoryRepository;

    public void saveFactory(MultipartFile file) {
        try {
            List<Factory> factories = ExcelHelperComplete.excelToFactories(file.getInputStream());
            factoryRepository.saveAll(factories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Factory> getAllFactories() {
        return factoryRepository.findAll();
    }

    public void deleteFactory(long id){
        try {
            factoryRepository.deleteById(id);
        } catch (ConstraintViolationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Factory> getFactoryById(long id){
        try {
            return factoryRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Autowired
    CarRepository carRepository;

    public void saveCar(MultipartFile file) {
        try {
            List<Car> cars = ExcelHelperComplete.excelToCars(file.getInputStream());
            carRepository.saveAll(cars);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(long id){
        carRepository.deleteById(id);
    }

    public Optional<Car> getCarById(long id){
            return carRepository.findById(id);
    }

    public Factory updateFactory(long id, Factory factoryToUpdate){
            factoryToUpdate.setId(id);
            return factoryRepository.save(factoryToUpdate);
    }

    public Car updateCar(long id, Car carToUpdate){
        carToUpdate.setId(id);
        return carRepository.save(carToUpdate);
    }
}