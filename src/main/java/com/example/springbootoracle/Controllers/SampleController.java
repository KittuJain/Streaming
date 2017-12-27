package com.example.springbootoracle.Controllers;

import com.example.springbootoracle.Models.Sample;
import com.example.springbootoracle.Repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
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
}
