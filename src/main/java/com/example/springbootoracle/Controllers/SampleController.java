package com.example.springbootoracle.Controllers;

import com.example.springbootoracle.Models.Sample;
import com.example.springbootoracle.Repositories.SampleRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
public class SampleController {

    @Autowired
    SampleRepository sampleRepository;

    @Transactional
    @GetMapping(value = "/allSamples/stream")
    public String getWithStreaming() {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        Stream<Sample> samples = sampleRepository.getAll();
        samples.forEach(sample -> System.out.println(sample.col1));
        stopwatch.stop();
        long timeTaken = stopwatch.getTotalTimeMillis();
        return String.valueOf(timeTaken);
    }

    @Transactional
    @GetMapping(value = "/allSamples/streamtenk")
    public String getFirst10kWithStreaming() {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        Stream<Sample> samples = sampleRepository.getFirst10000();
        samples.forEach(sample -> System.out.println(sample.col1));
        stopwatch.stop();
        long timeTaken = stopwatch.getTotalTimeMillis();
        return String.valueOf(timeTaken);
    }

    @Transactional
    @GetMapping(value = "/samples")
    public String getWithoutStreaming() {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        List<Sample> samples = sampleRepository.findAll();
        samples.forEach(sample -> System.out.println(sample.col1));
        stopwatch.stop();
        long timeTaken = stopwatch.getTotalTimeMillis();
        return String.valueOf(timeTaken);
    }

    @Transactional
    @GetMapping(value = "/tenksamples")
    public String getFirst10kWithoutStreaming() {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        List<Sample> samples = sampleRepository.getFirst10000WithoutStreaming();
        samples.forEach(sample -> System.out.println(sample.col1));
        stopwatch.stop();
        long timeTaken = stopwatch.getTotalTimeMillis();
        return String.valueOf(timeTaken);
    }

    @Transactional
    @GetMapping(value = "/insert")
    public void insertIntoDB() {
        for (int i = 0; i < 300000; i++) {
            System.out.println("Inserting...");
            sampleRepository.save(new Sample("Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda", "Honda"));
        }
    }

    @Transactional
    @GetMapping(value = "/excel")
    public String getExcel() {

        Stream<Sample> sampleStream = sampleRepository.getFirst10000();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("samples");

        AtomicInteger rownum= new AtomicInteger();
        sampleStream.forEach(sample -> {
            Row row = sheet.createRow(rownum.get());
            rownum.getAndIncrement();
            Cell cell0 = row.createCell(0);
            cell0.setCellValue((Integer)sample.id);
            Cell cell1 = row.createCell(1);
            cell1.setCellValue((String)sample.col1);
            Cell cell2 = row.createCell(2);
            cell2.setCellValue((String)sample.col2);
            Cell cell3 = row.createCell(3);
            cell3.setCellValue((String)sample.col3);
            Cell cell4 = row.createCell(4);
            cell4.setCellValue((String)sample.col4);
            Cell cell5 = row.createCell(5);
            cell5.setCellValue((String)sample.col5);
            Cell cell6 = row.createCell(6);
            cell6.setCellValue((String)sample.col6);
            Cell cell7 = row.createCell(7);
            cell7.setCellValue((String)sample.col7);
            Cell cell8 = row.createCell(8);
            cell8.setCellValue((String)sample.col8);
            Cell cell9 = row.createCell(9);
            cell9.setCellValue((String)sample.col9);
            Cell cell10 = row.createCell(10);
            cell10.setCellValue((String)sample.col10);
            Cell cell11 = row.createCell(11);
            cell11.setCellValue((String)sample.col11);
            Cell cell12 = row.createCell(12);
            cell12.setCellValue((String)sample.col12);
            Cell cell13 = row.createCell(13);
            cell13.setCellValue((String)sample.col13);
            Cell cell14 = row.createCell(14);
            cell14.setCellValue((String)sample.col14);
        });

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("sampleexcel.xlsx"));
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        return "Excel created!";
    }
}
