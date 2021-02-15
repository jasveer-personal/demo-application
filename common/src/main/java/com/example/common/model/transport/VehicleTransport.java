package com.example.common.model.transport;

public class VehicleTransport {

    private Integer vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private CustomerTransport customerTransport;

    public CustomerTransport getCustomerTransport() {
        return customerTransport;
    }

    public void setCustomerTransport(CustomerTransport customerTransport) {
        this.customerTransport = customerTransport;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}

