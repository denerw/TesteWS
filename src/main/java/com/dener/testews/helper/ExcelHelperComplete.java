package com.dener.testews.helper;

import com.dener.testews.model.Car;
import com.dener.testews.model.Factory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelHelperComplete {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Factory_Id", "Factory_Name", "Model", "Year", "Fuel", "Doors", "Cost", "Color" };
    static String SHEET = "Completa";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Factory> excelToFactories(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Factory> factories = new ArrayList<Factory>();

            Set<Car> factoriesCars = new HashSet<>(); // gera o set de carros da fábrica que está sendo iterada


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Factory factory = new Factory();

                Car factoriesCar = new Car();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            factoriesCar.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            factory.setId((long) currentCell.getNumericCellValue());
                            factoriesCar.setFactory_id((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            factory.setName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            factoriesCar.setModel(currentCell.getStringCellValue());
                            break;

                        case 4:
                            factoriesCar.setYear((long) currentCell.getNumericCellValue());
                            break;

                        case 5:
                            factoriesCar.setFuel(currentCell.getStringCellValue());
                            break;

                        case 6:
                            factoriesCar.setDoors((long) currentCell.getNumericCellValue());
                            break;

                        case 7:
                            factoriesCar.setCost((long) currentCell.getNumericCellValue());
                            break;

                        case 8:
                            factoriesCar.setColor(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                factoriesCars.add(factoriesCar);
//                factory.setCars(factoriesCars);
                factories.add(factory);
            }

            workbook.close();

            return factories;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Car> excelToCars(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Car> cars = new ArrayList<Car>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Car car = new Car();
                Factory carsFactory = new Factory();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            car.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            carsFactory.setId((long) currentCell.getNumericCellValue());
                            car.setFactory_id((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            carsFactory.setName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            car.setModel(currentCell.getStringCellValue());
                            break;

                        case 4:
                            car.setYear((long) currentCell.getNumericCellValue());
                            break;

                        case 5:
                            car.setFuel(currentCell.getStringCellValue());
                            break;

                        case 6:
                            car.setDoors((long) currentCell.getNumericCellValue());
                            break;

                        case 7:
                            car.setCost((long) currentCell.getNumericCellValue());
                            break;

                        case 8:
                            car.setColor(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }
//                car.setFactory(carsFactory);
                cars.add(car);
            }

            workbook.close();

            return cars;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

//    public static void linkCarsToFactories(List<Car> cars, List<Factory> factories){
//        long factoryId =
//    }
}