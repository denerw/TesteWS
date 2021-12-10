package com.dener.testews.controller;

import com.dener.testews.helper.ExcelHelperCars;
import com.dener.testews.helper.ExcelHelperComplete;
import com.dener.testews.helper.ExcelHelperFactories;
import com.dener.testews.message.ResponseMessage;
import com.dener.testews.model.Car;
import com.dener.testews.model.Factory;
import com.dener.testews.repository.CarRepository;
import com.dener.testews.repository.FactoryRepository;
import com.dener.testews.service.ExcelServiceCar;
import com.dener.testews.service.ExcelServiceComplete;
import com.dener.testews.service.ExcelServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    ExcelServiceFactory fileServiceFactory;

    @Autowired
    ExcelServiceCar fileServiceCar;

    @Autowired
    ExcelServiceComplete fileServiceComplete;

    @Autowired
    CarRepository carRepository;

    @Autowired
    FactoryRepository factoryRepository;

    @PostMapping("/uploadfactories")
    public ResponseEntity<ResponseMessage> uploadFileFactory(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelperFactories.hasExcelFormat(file)) {
            try {
                fileServiceFactory.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/factories")
    public ResponseEntity<List<Factory>> getAllFactories() {
        try {
            List<Factory> factories = fileServiceFactory.getAllFactories();

            if (factories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(factories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadcars")
    public ResponseEntity<ResponseMessage> uploadFileCar(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelperCars.hasExcelFormat(file)) {
            try {
                fileServiceCar.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> cars = fileServiceCar.getAllCars();

            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadcomplete")
    public ResponseEntity<ResponseMessage> uploadFileComplete(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelperComplete.hasExcelFormat(file)) {
            try {
                fileServiceComplete.saveFactory(file);
                fileServiceComplete.saveCar(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @DeleteMapping("/deletecarsandfactories")
    public ResponseEntity<ResponseMessage> deleteEverything() {
        String message = "Just deleted all the cars and factories :(";
        carRepository.deleteAll();
        factoryRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}