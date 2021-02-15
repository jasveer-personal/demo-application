package com.example.vehicleservice.controller;

import com.example.common.model.transport.VehicleTransport;
import com.example.common.model.vehicle.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/vehicle")
    public List<Vehicle> getVehicle() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/vehicle/{id}")
    public Vehicle getCustomer(@PathVariable Integer id) {

        return vehicleRepository.findById(id).orElse(new Vehicle());
    }

    @PostMapping(value = "/vehicle", consumes = "application/json")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping(value = "/custvehicle/{id}")
    public List<VehicleTransport> customerVehicle(@PathVariable(name = "id") Integer custId) {
        List<VehicleTransport> vehicleTransportList = new ArrayList<>();
        for(Vehicle vehicle :  vehicleRepository.findByCustomer_Id(custId)) {
            VehicleTransport transport = new VehicleTransport();
            transport.setVehicleId(vehicle.getVehicleId());
            transport.setVehicleName(vehicle.getVehicleName());
            transport.setRegistrationNumber(vehicle.getRegistrationNumber());

            vehicleTransportList.add(transport);
        }
        return vehicleTransportList;
    }
}

