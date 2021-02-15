package com.example.vehicleservice.repository;

import com.example.common.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByCustomer_Id(Integer id);
}
