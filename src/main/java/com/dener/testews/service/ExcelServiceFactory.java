package com.dener.testews.service;

import java.io.IOException;
import java.util.List;

import com.dener.testews.helper.ExcelHelperFactories;
import com.dener.testews.model.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dener.testews.repository.FactoryRepository;


@Service
public class ExcelServiceFactory {
    @Autowired
    FactoryRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Factory> factories = ExcelHelperFactories.excelToFactories(file.getInputStream());
            repository.saveAll(factories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Factory> getAllFactories() {
        return repository.findAll();
    }
}