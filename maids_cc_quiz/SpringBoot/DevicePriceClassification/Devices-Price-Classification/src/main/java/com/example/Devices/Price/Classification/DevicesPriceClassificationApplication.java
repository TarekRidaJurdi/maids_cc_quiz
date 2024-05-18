package com.example.Devices.Price.Classification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for the Devices Price Classification application.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.example.Devices.Price.Classification"})
public class DevicesPriceClassificationApplication {

    /**
     * Main method to start the application.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(DevicesPriceClassificationApplication.class, args);
    }
}
