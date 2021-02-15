package com.example.common.model.vehicle;

import com.example.common.model.customer.Customer;

import javax.persistence.*;

@Entity
@Table(name = "vehicle", schema = "demo")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicleid")
    private Integer vehicleId;

    @Column(name = "vehiclename")
    private String vehicleName;

    @Column(name = "regnumber")
    private String registrationNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
