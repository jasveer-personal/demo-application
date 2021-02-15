package com.example.common.model.transport;

import com.sun.corba.se.spi.activation.ServerAlreadyActiveHolder;

import java.util.List;

public class CustomerTransport {

    private Integer id;
    private String name;
    private String email;
    private List<VehicleTransport> vehicleTransport;

    public List<VehicleTransport> getVehicleTransport() {
        return vehicleTransport;
    }

    public void setVehicleTransport(List<VehicleTransport> vehicleTransport) {
        this.vehicleTransport = vehicleTransport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
