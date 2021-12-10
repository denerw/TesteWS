package com.dener.testews.service;

import com.dener.testews.helper.ExcelHelperCars;
import com.dener.testews.helper.ExcelHelperComplete;
import com.dener.testews.helper.ExcelHelperFactories;
import com.dener.testews.model.Car;
import com.dener.testews.model.Factory;
import com.dener.testews.repository.CarRepository;
import com.dener.testews.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


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


}