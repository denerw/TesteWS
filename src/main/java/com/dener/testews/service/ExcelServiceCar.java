package com.dener.testews.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.dener.testews.helper.ExcelHelperCars;
import com.dener.testews.model.Car;
import com.dener.testews.model.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dener.testews.repository.CarRepository;

import javax.persistence.Id;


@Service
public class ExcelServiceCar {
    @Autowired
    CarRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Car> cars = ExcelHelperCars.excelToCars(file.getInputStream());
            repository.saveAll(cars);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public void deleteAllCars(){
        repository.deleteAll();
    }
}