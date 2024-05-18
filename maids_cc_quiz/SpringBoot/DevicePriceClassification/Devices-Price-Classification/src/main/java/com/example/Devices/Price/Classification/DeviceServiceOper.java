package com.example.Devices.Price.Classification;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of DeviceService interface for managing devices.
 */
@Service
public class DeviceServiceOper implements DeviceService {

    private final List<Device> devices; // List of devices

    /**
     * Constructs a DeviceServiceTarek object and initializes devices list.
     */
    public DeviceServiceOper() {
        this.devices = new ArrayList<>();
        // Load devices from CSV file
        this.devices.addAll(getAllDevicesFromCsv());
    }

    @Override
    public Device getDeviceById(Integer id) {
        // Find device by ID
        for (Device device : devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null; // Device not found
    }

    @Override
    public List<Device> getAllDevicesFromCsv() {
        List<Device> devices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:test.csv")))) {
            // Read CSV file
            String header = reader.readLine(); // Skip header line
            String[] headers = header.split(",");
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == headers.length) {
                    // Create device object and populate fields
                    Device device = new Device();
                    for (int i = 0; i < headers.length; i++) {
                        switch (headers[i]) {
                            case "id":
                                device.setId(Integer.parseInt(fields[i]));
                                break;
                            case "battery_power":
                                device.setBattery_power(Integer.parseInt(fields[i]));
                                break;
                            case "blue":
                                device.setBlue(Boolean.parseBoolean(fields[i]));
                                break;
                            // Add cases for other fields...
                        }
                    }
                    devices.add(device); // Add device to list
                } else {
                    // Log or handle malformed lines
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
        return devices;
    }

    // Path to CSV file
    private static final String CSV_FILE_PATH = "classpath:test.csv";

    /**
     * Adds a new device to the list and writes it to CSV file.
     * @param device The device to add.
     * @return The added device.
     */
    public Device addDevice(Device device) {
        writeToCSV(device); // Write device to CSV file
        devices.add(device); // Add device to list
        return device;
    }

    // Write device to CSV file
    private void writeToCSV(Device device) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getCSVFile(), true))) {
            String csvLine = constructCSVLine(device); // Construct CSV line
            writer.write(csvLine); // Write CSV line to file
        } catch (IOException e) {
            // Rethrow a custom exception to handle the error
            throw new RuntimeException("Error occurred while writing to CSV file", e);
        }
    }

    // Get CSV file object
    private File getCSVFile() {
        try {
            return ResourceUtils.getFile(CSV_FILE_PATH); // Get CSV file from path
        } catch (IOException e) {
            // Rethrow a custom exception to handle the error
            throw new RuntimeException("Error loading CSV file", e);
        }
    }

    // Construct CSV line from device object
    private String constructCSVLine(Device device) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(device.getId()).append(",")
                .append(device.getBattery_power()).append(",")
                .append(device.getBlue()).append(",")
                // Append other device fields...
                .append("\n");
        return stringBuilder.toString();
    }
}
