package com.example.Devices.Price.Classification;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Device entities.
 * Extends JpaRepository to provide CRUD operations for Device entities.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
