package com.dener.testews.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.dener.testews.model.Car;

public class ExcelHelperCars {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Factory_Id", "Model", "Year", "Fuel", "Doors", "Cost", "Color" };
    static String SHEET = "Cars";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
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

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            car.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            car.setFactory_id((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            car.setModel(currentCell.getStringCellValue());
                            break;

                        case 3:
                            car.setYear((long) currentCell.getNumericCellValue());
                            break;

                        case 4:
                            car.setFuel(currentCell.getStringCellValue());
                            break;

                        case 5:
                            car.setDoors((long) currentCell.getNumericCellValue());
                            break;

                        case 6:
                            car.setCost((long) currentCell.getNumericCellValue());
                            break;

                        case 7:
                            car.setColor(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                        }

                        cellIdx++;
                    }

                cars.add(car);
            }

            workbook.close();

            return cars;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}