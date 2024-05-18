/*
 * This package contains classes related to device price classification.
 */
package com.example.Devices.Price.Classification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.transaction.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
 * This class represents the REST controller for managing devices.
 */
@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;

    /*
     * Constructor for DeviceController.
     *
     * @param deviceService The service responsible for managing devices.
     */
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /*
     * Retrieves all devices from CSV.
     *
     * @return ResponseEntity containing the list of devices and HTTP status.
     */
    @PostMapping("/devices/")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevicesFromCsv();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    /*
     * Retrieves a device by its ID.
     *
     * @param id The ID of the device to retrieve.
     * @return ResponseEntity containing the device or not found status.
     */
    @GetMapping("/devices/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Integer id) {
        Device device = deviceService.getDeviceById(id);
        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Adds a new device.
     *
     * @param device The device to add.
     * @return ResponseEntity containing the added device or error message.
     */
    @PostMapping("/devices")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        try {
            Device newDevice = deviceService.addDevice(device);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDevice);
        } catch (IllegalArgumentException e) {
            // Handle invalid request data (e.g., validation error)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Handle other runtime exceptions
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * Predicts the price classification for a device.
     *
     * @param deviceId The ID of the device for which to predict the price classification.
     * @return ResponseEntity containing the prediction result or error message.
     */
    @PostMapping("/predict/{deviceId}")
    @Transactional
    public ResponseEntity<?> predict(@PathVariable Integer deviceId) {
        System.out.println("deviceId: " + deviceId);

        // Construct the URL with the deviceId
        String apiUrl = "http://127.0.0.1:8000/predict/" + deviceId;

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send a POST request to the external API and get the response
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, null, String.class);

        // Create ObjectMapper for JSON processing
        ObjectMapper objectMapper = new ObjectMapper();

        // Extract the prediction result from the response
        if (response.getStatusCode().is2xxSuccessful()) {
            String predictionResult = response.getBody(); // Assuming the response is the prediction result

            // Create a JSON object to hold device parameters and prediction result
            ObjectNode jsonNode = objectMapper.createObjectNode();
            // Set device parameters
            jsonNode.put("deviceId", deviceId);
            // You can set other device parameters as needed
            // Set prediction result
            jsonNode.put("predictionResult", predictionResult);

            // Write the JSON object to 'predict.json' file
            try {
                File predictFile = new File("predict.json");
                objectMapper.writeValue(predictFile, jsonNode);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Failed to save prediction details to 'predict.json' for Device ID: " + deviceId);
            }

            // Return the prediction response along with the success message
            return ResponseEntity.ok(predictionResult);
        } else {
            return ResponseEntity.badRequest().body("Failed to fetch prediction for Device ID: " + deviceId);
        }
    }
}
