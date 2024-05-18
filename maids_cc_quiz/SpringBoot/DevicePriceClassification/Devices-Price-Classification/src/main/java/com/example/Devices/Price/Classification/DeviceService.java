package com.example.Devices.Price.Classification;

import java.util.List;

/**
 * Interface for managing devices.
 */
public interface DeviceService {

    /**
     * Retrieves all devices from a CSV file.
     * @return List of devices.
     */
    List<Device> getAllDevicesFromCsv();

    /**
     * Retrieves a device by its ID.
     * @param id The ID of the device.
     * @return The device with the specified ID, or null if not found.
     */
    Device getDeviceById(Integer id);

    /**
     * Adds a new device.
     * @param device The device to add.
     * @return The added device.
     */
    Device addDevice(Device device);
}

